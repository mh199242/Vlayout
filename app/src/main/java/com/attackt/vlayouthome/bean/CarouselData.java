package com.attackt.vlayouthome.bean;

import java.util.List;

/**
 * 轮播图bean
 */
public class CarouselData {

    /**
     * error_code : 0
     * carousel : [{"url":"","picture":"http://ofedrm17c.bkt.clouddn.com/orange/14774832526275.jpg","associated_id":0,"id":1,"title":"轻松Hold住敏感宝宝"},{"url":"","picture":"http://ofedrm17c.bkt.clouddn.com/orange/14775521998552.jpg","associated_id":0,"id":3,"title":"洗护专场"},{"url":"","picture":"http://ofedrm17c.bkt.clouddn.com/orange/14779036848805.jpg","associated_id":0,"id":4,"title":"外面辣么冷在家玩吧"}]
     */

    private int error_code;
    private List<CarouselBean> carousel;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<CarouselBean> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CarouselBean> carousel) {
        this.carousel = carousel;
    }

    public static class CarouselBean {
        /**
         * url :
         * picture : http://ofedrm17c.bkt.clouddn.com/orange/14774832526275.jpg
         * associated_id : 0
         * id : 1
         * title : 轻松Hold住敏感宝宝
         */

        private String url;
        private String picture;
        private int associated_id;
        private int id;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getAssociated_id() {
            return associated_id;
        }

        public void setAssociated_id(int associated_id) {
            this.associated_id = associated_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
