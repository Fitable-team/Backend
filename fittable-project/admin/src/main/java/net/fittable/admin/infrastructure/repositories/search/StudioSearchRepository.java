package net.fittable.admin.infrastructure.repositories.search;

import lombok.extern.slf4j.Slf4j;
import net.fittable.admin.view.dto.client.request.LocationStudioSearchDto;
import net.fittable.domain.search.SearchableStudio;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StudioSearchRepository {

    @Autowired
    private RestHighLevelClient esClient;

    public List<SearchableStudio> searchForStudio(LocationStudioSearchDto dto) {
        SearchRequest request = new SearchRequest("studio");
        SearchResponse response = null;

        SearchSourceBuilder source = new SearchSourceBuilder();
        BoolQueryBuilder studioSearchConditions = QueryBuilders.boolQuery();

        if(dto.isLocationBasedSearch()) {
            studioSearchConditions.filter(QueryBuilders.geoDistanceQuery("location")
                    .point(dto.getLatitude(), dto.getLongitude())
                    .distance(Double.valueOf(dto.getWithinKilometer()), DistanceUnit.KILOMETERS));
        }

        if(StringUtils.isNotEmpty(dto.getTownName())) {
            studioSearchConditions.must(QueryBuilders.matchQuery("location", dto.getTownName()));
        }

        source.query(studioSearchConditions);
        try {
            response = esClient.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("error while searching studios: ", e);
            return Collections.emptyList();
        }

        if(response.getHits().getTotalHits().value == 0) {
            return Collections.emptyList();
        }

        return Arrays.asList(response.getHits().getHits())
                .stream()
                .sorted((a, b) -> (int)(a.getScore() - b.getScore()))
                .map(SearchHit::getSourceAsMap)
                .map(map -> {
                    SearchableStudio studio = SearchableStudio.emptyObject();

                    studio.setName((String)map.get("name"));
                    studio.setLowerDistrict((String)map.get("lowerDistrict"));
                    studio.setSuperDistrict((String)map.get("superDistrict"));

                    Map<String, Double> locationMap = (Map<String, Double>)map.get("location");
                    studio.setLatitude(locationMap.get("latitude"));
                    studio.setLongitude(locationMap.get("longitude"));

                    return studio;
                }).collect(Collectors.toList());
    }

    public void saveNewStudio(SearchableStudio studio) {
        IndexRequest indexRequest = new IndexRequest("studio");


    }
}
