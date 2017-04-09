package com.attackt.vlayouthome.bean;

import java.util.List;

/**
 * 热卖列表bean
 */
public class Hot {

    /**
     * items : [{"name":"德国babydream婴幼儿2合1洗发水沐浴乳安全无泪抗敏","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14774562092300.jpg","price":39,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"bd0009","stock_number":100,"id":9},{"name":"百年老字号品牌Hamleys消防员主题玩具 豪华场景模型","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14777180892413.jpg","price":610,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"ADS160505SAL001","stock_number":100,"id":20},{"name":"德国Perlodent med婴幼儿童低氟可吞咽牙膏草莓味6岁以上","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14774473601119.jpg","price":26,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"pm002","stock_number":100,"id":6},{"name":"德国Perlodent med婴幼儿童低氟可吞咽牙膏草莓味0-6岁","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14774550137524.jpg","price":26,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"pm001","stock_number":100,"id":7},{"name":"儿童用品专家Tommee Tippee Closer to Nature系列 奶瓶保温袋（两个装）","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14777985244741.jpg","price":203,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"M-B160106LUX001","stock_number":100,"id":30},{"name":"德国Babydream洗发沐浴二合一Waschlotion & Shampoo 2 in1  ","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14774621387239.jpg","price":35,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"bd0002","stock_number":100,"id":18},{"name":"蝴蝶玫瑰Jottum彩印粉红无袖连衣裙","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14811688721556.jpg","price":1540,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"M-B160907GAL001","stock_number":100,"id":302},{"name":"印象派水彩城市JottumA字裙","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14811687203299.jpg","price":530,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"M-B160907GAL002","stock_number":100,"id":301},{"name":"夜晚的西西里Jottum彩印无袖连衣裙","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14811661487042.jpg","price":1646,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"M-B160907GAL003","stock_number":100,"id":300},{"name":"夜晚的城市Jottum彩印长袖连衣裙","cover":"http://ofedrm17c.bkt.clouddn.com/orange/14811656273893.jpg","price":690,"cover1":"http://ofedrm17c.bkt.clouddn.com/orange/","number":"M-B160907GAL004","stock_number":100,"id":299}]
     * error_code : 0
     */

    private int error_code;
    private List<ItemsBean> items;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * name : 德国babydream婴幼儿2合1洗发水沐浴乳安全无泪抗敏
         * cover : http://ofedrm17c.bkt.clouddn.com/orange/14774562092300.jpg
         * price : 39
         * cover1 : http://ofedrm17c.bkt.clouddn.com/orange/
         * number : bd0009
         * stock_number : 100
         * id : 9
         */

        private String name;
        private String cover;
        private int price;
        private String cover1;
        private String number;
        private int stock_number;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCover1() {
            return cover1;
        }

        public void setCover1(String cover1) {
            this.cover1 = cover1;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getStock_number() {
            return stock_number;
        }

        public void setStock_number(int stock_number) {
            this.stock_number = stock_number;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
