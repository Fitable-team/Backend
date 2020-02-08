package net.fittable.admin.infrastructure.batch;

import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.domain.search.SearchableStudio;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudioInformationFullIndexJob {

    private final StudioRepository studioRepository;
    private final RestHighLevelClient client;

    public StudioInformationFullIndexJob(StudioRepository studioRepository, RestHighLevelClient client) {
        this.studioRepository = studioRepository;
        this.client = client;
    }

    public void indexAll() {
        List<SearchableStudio> searchableStudios = studioRepository.findAll().stream()
                .map(SearchableStudio::fromStudio)
                .collect(Collectors.toList());


        List<XContentBuilder> indexableStudios = new ArrayList<>();

        for(SearchableStudio s: searchableStudios) {
            indexableStudios.add(this.toIndexableDocument(s));
        }

            indexableStudios.forEach(s -> {
                try {
                    client.index(new IndexRequest("studios").id("").source(s), RequestOptions.DEFAULT);
                } catch (IOException e) {

                }
            });
    }

    private XContentBuilder toIndexableDocument(SearchableStudio studio) {
        XContentBuilder indexableDocument = null;
        try {
            indexableDocument = XContentFactory.jsonBuilder();
            indexableDocument.startObject();
            {
                indexableDocument.field("studio-name", studio.getName());
                indexableDocument.field("super-district", studio.getSuperDistrict());
                indexableDocument.field("lower-district", studio.getLowerDistrict());
                indexableDocument.field("town", studio.getTown());

            }
        } catch (IOException e) {

        }
        return indexableDocument;
    }
}
