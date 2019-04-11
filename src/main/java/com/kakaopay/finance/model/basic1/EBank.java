package com.kakaopay.finance.model.basic1;

public enum EBank {
    molit("주택도시기금", "bnk0001")
    , kb("국민은행", "bnk0002")
    , wr("우리은행", "bnk0003")
    , sh("신한은행", "bnk0004")
    , citi("한국시티은행", "bnk0005")
    , hn("하나은행", "bnk0006")
    , nh("농협/수협은행", "bnk0007")
    , keb("외환은행", "bnk0008")
    , ect("기타은행", "bnk0009")
    ;

    private String bankName;
    private String bankCode;

    EBank(String bankName, String bankCode){
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    public String getBankName(){
        return bankName;
    }

    public String getBankCode(){
        return bankCode;
    }
}


//package com.kakaopay.finance.model.basic1;
//
//public enum EBank {
//    molit("주택도시기금", 0)
//    , kb("국민은행", 1)
//    , wr("우리은행", 2)
//    , sh("신한은행", 3)
//    , citi("한국시티은행", 4)
//    , hn("하나은행", 5)
//    , nh("농협/수협은행", 6)
//    , keb("외환은행", 7)
//    , ect("기타은행", 8)
//    ;
//
//    private String bankName;
//    private int sequence;
//
//    EBank(String bankName, int sequence){
//        this.bankName = bankName;
//        this.sequence = sequence;
//    }
//
//    public String getBankName(){
//        return bankName;
//    }
//}