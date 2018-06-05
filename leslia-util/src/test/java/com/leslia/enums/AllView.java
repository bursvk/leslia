package com.leslia.enums;

public enum  AllView {

    init(1001,"this is init"){
        public void aa(){
            System.out.println("init aa");
        }
    },
    between(1002,"this is between"){
        public void aa(){
            System.out.println("between aa");
        }
    },
    end(1003,"this is end"){
        public void aa(){
            System.out.println("end aa");
        }
    };

    private int code;
    private String des;

    private AllView(int code,String des){
        this.code=code;
        this.des=des;
    }

    abstract void aa();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }



}
