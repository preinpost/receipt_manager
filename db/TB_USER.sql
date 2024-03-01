CREATE TABLE TB_USER
(
    uid      varchar(36)   NOT NULL,
    user_id  varchar(30)   NOT NULL,
    password varchar(1000),
    passkey  text,
    PRIMARY KEY (uid)
);

COMMENT ON TABLE TB_USER IS '사용자';

COMMENT ON COLUMN TB_USER.uid IS '사용자 유니크 식별자 (UUIDv7)';

COMMENT ON COLUMN TB_USER.user_id IS '사용자 아이디';

COMMENT ON COLUMN TB_USER.password IS '사용자 비밀번호';

COMMENT ON COLUMN TB_USER.passkey IS '패스키 데이터 (base64)';