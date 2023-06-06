package com.laptrinhjavaweb.enums;

public enum TransactionEnum {
    QUA_TRINH_CSKH("QUÁ TRÌNH CSKH"),
    DAN_DI_XEM("DẪN ĐI XEM");

    private final String transactionValue;

    TransactionEnum(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getTransactionValue() {
        return transactionValue;
    }
}
