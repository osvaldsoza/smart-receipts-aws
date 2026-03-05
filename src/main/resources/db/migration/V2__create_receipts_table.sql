CREATE TABLE receipts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    user_id UUID NOT NULL,

    status VARCHAR(50) NOT NULL,

    description VARCHAR(255),

    amount NUMERIC(15,2),

    original_key VARCHAR(255),

    processed_key VARCHAR(255),

    file_data BYTEA NOT NULL,

    file_name VARCHAR(255) NOT NULL,

    file_content_type VARCHAR(100) NOT NULL,

    file_size BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_receipt_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_receipts_user_id
ON receipts(user_id);

CREATE INDEX idx_receipts_status
ON receipts(status);