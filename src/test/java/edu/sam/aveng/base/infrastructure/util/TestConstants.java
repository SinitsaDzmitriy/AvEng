package edu.sam.aveng.base.infrastructure.util;

public final class TestConstants {

    private TestConstants() {
    }

    public static final class Database {

        private Database() {

        }

        public static final class Tables {

            public static final class SimpleRecords {
                private SimpleRecords() {
                }

                public static final String TABLE_NAME = "simple_records";
                public static final String ID_COLUMN_NAME = "id";
                public static final String STRING_COLUMN_NAME = "string";
            }
        }
    }
}
