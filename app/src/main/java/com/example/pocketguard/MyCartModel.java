package com.example.pocketguard;




import java.io.Serializable;

    public class MyCartModel implements Serializable {
        String BookName;
        String BookPrice;
        String currentDate;
        String currentTime;
        String totalQuantity;
        String documentId;
        int totalPrice;

        public MyCartModel() {
        }

        public MyCartModel(String BookName, String BookPrice, String currentDate, String currentTime, String totalQuantity, int totalPrice,String documentId) {
            this.BookName = BookName;
            this.BookPrice = BookPrice;
            this.currentDate = currentDate;
            this.currentTime = currentTime;
            this.totalQuantity = totalQuantity;
            this.totalPrice = totalPrice;
            this.documentId = documentId;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getBookName() {
            return BookName;
        }

        public void setBookName(String BookName) {
            this.BookName = BookName;
        }

        public String getBookPrice() {
            return BookPrice;
        }

        public void setBookPrice(String BookPrice) {
            this.BookPrice = BookPrice;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public void setCurrentDate(String currentDate) {
            this.currentDate = currentDate;
        }

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

        public String getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(String totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
