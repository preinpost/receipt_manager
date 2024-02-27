CREATE TABLE TB_RECEIPT
(
    user_id          varchar(36)   NOT NULL,
    receipt_year     varchar(4)    NOT NULL,
    receipt_date     varchar(4)    NOT NULL,
    seq              varchar(1000) NOT NULL,
    payment_date     varchar(8)    NOT NULL,
    payment_amount   varchar(16)  ,
    payment_location varchar(200) ,
    PRIMARY KEY (user_id, receipt_year, receipt_date, seq)
);

COMMENT ON TABLE TB_RECEIPT IS '영수증 내역';

COMMENT ON COLUMN TB_RECEIPT.user_id IS '사용자 아이디';

COMMENT ON COLUMN TB_RECEIPT.receipt_year IS '영수증 내역 연';

COMMENT ON COLUMN TB_RECEIPT.receipt_date IS '영수증 내역 월';

COMMENT ON COLUMN TB_RECEIPT.seq IS '영주증 내역 순번';

COMMENT ON COLUMN TB_RECEIPT.payment_date IS '결제날짜';

COMMENT ON COLUMN TB_RECEIPT.payment_amount IS '결제금액';

COMMENT ON COLUMN TB_RECEIPT.payment_location IS '결제한 곳';

CREATE TABLE TB_USER
(
    uid      varchar(36)   NOT NULL PRIMARY KEY ,
    user_id  varchar(30)   NOT NULL,
    password varchar(1000),
    passkey  varchar(8000),
    PRIMARY KEY (uid)
);

COMMENT ON TABLE TB_USER IS '사용자';

COMMENT ON COLUMN TB_USER.uid IS '사용자 유니크 식별자 (UUIDv7)';

COMMENT ON COLUMN TB_USER.user_id IS '사용자 아이디';

COMMENT ON COLUMN TB_USER.password IS '사용자 비밀번호';

COMMENT ON COLUMN TB_USER.passkey IS '패스키 데이터 (base64)';