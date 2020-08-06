CREATE TABLE payment
(
    id             bigserial     NOT NULL,
    client_id      bigint        NULL,
    status         varchar(150)  NULL,
    service_id     bigint        NULL,
    date_created   timestamp     NOT NULL,
    date_completed timestamp     NULL,
    amount         numeric(19,2),
    client_comment varchar(1024) NULL,
    idempotancy_key uuid NOT NULL,
    CONSTRAINT payment_pk PRIMARY KEY (id)
);
