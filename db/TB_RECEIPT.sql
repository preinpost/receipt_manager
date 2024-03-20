CREATE TABLE TB_RECEIPT
(
    user_id          varchar(30)    NOT NULL,
    payment_date     varchar(8)     NOT NULL,
    seq              numeric        NOT NULL,
    payment_amount   varchar(16)            ,
    payment_location varchar(200)           ,
    del_yn           char(1)     DEFAULT 'N',
    PRIMARY KEY (user_id, seq, payment_date)
);

COMMENT ON TABLE TB_RECEIPT IS '영수증 내역';

COMMENT ON COLUMN TB_RECEIPT.user_id IS '사용자 아이디';

COMMENT ON COLUMN TB_RECEIPT.payment_date IS '결제날짜';

COMMENT ON COLUMN TB_RECEIPT.seq IS '영주증 내역 순번';

COMMENT ON COLUMN TB_RECEIPT.payment_amount IS '결제금액';

COMMENT ON COLUMN TB_RECEIPT.payment_location IS '결제한 곳';

COMMENT ON COLUMN TB_RECEIPT.del_yn IS '삭제 여부';
