package com.kaffka.yahooweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kaffka on 6/24/2017.
 */

public class Weather {
    private Query query;

    public Query getQuery() {
        return query;
    }

    public class Query {
        private int count;
        @SerializedName("created")
        private String createdDate;
        @SerializedName("lang")
        private String language;
        private Results results;

        public int getCount() {
            return count;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getLanguage() {
            return language;
        }

        public Results getResults() {
            return results;
        }
    }

    public class Results {
        private Channel channel;

        public Channel getChannel() {
            return channel;
        }
    }

    public class Channel {
        private Item item;
        private String title;

        public String getTitle() {
            return title;
        }

        public Item getItem() {
            return item;
        }
    }

    public class Item {
        private String description;

        public String getDescription() {
            return description;
        }
    }
}
