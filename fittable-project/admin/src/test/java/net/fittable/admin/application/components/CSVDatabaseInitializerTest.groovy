package net.fittable.admin.application.components

import org.junit.Before
import org.junit.Test

class CSVDatabaseInitializerTest {

    CSVDatabaseInitializer initializer

    @Before
    void setup() {
        initializer = new CSVDatabaseInitializer(null, "/Users/bomeehouse/Desktop/excel_studio_info.csv")
    }

    @Test
    void parse() {
        initializer.setStudioDatabase()
    }
}
