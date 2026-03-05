# Smart Receipts AWS - File Upload Guide

## Campos de Upload Adicionados

### Receipt.java (Entity)
- `byte[] fileData` - Armazena os dados binários do arquivo
- `String fileName` - Nome original do arquivo
- `String fileContentType` - Tipo MIME do arquivo (ex: application/pdf)
- `Long fileSize` - Tamanho do arquivo em bytes

### ReceiptDTO.java (Data Transfer Object)
- `MultipartFile file` - Arquivo enviado pelo usuário
- `String fileName` - Nome original do arquivo
- `String fileContentType` - Tipo MIME do arquivo
- `Long fileSize` - Tamanho do arquivo

## Endpoints API

### 1. Criar Receipt SEM arquivo
```bash
POST /api/receipts

{
  "userId": "user-123",
  "status": "PENDING",
  "originalKey": "key-123",
  "processedKey": null
}
```

### 2. Criar Receipt COM arquivo (Recomendado)
```bash
POST /api/receipts/upload

Request:
- userId (string) - ID do usuário
- status (string) - Status do recebimento
- originalKey (string) - Chave original
- processedKey (string) - Chave processada
- file (multipart file) - Arquivo a fazer upload
```

**Exemplo com cURL:**
```bash
curl -X POST "http://localhost:8080/api/receipts/upload" \
  -F "userId=user-123" \
  -F "status=PENDING" \
  -F "originalKey=key-123" \
  -F "processedKey=key-processed-123" \
  -F "file=@/path/to/receipt.pdf"
```

### 3. Obter Receipt por ID
```bash
GET /api/receipts/{id}

Response:
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "userId": "user-123",
  "status": "PENDING",
  "originalKey": "key-123",
  "processedKey": "key-processed-123",
  "fileName": "receipt.pdf",
  "fileContentType": "application/pdf",
  "fileSize": 102400
}
```

### 4. Listar todos os Receipts
```bash
GET /api/receipts

Response: Lista de todos os receipts
```

### 5. Listar Receipts de um usuário
```bash
GET /api/receipts/user/{userId}

Response: Lista de receipts do usuário específico
```

### 6. Atualizar Receipt SEM arquivo
```bash
PUT /api/receipts/{id}

{
  "userId": "user-123",
  "status": "PROCESSED",
  "originalKey": "key-123",
  "processedKey": "key-processed-123"
}
```

### 7. Atualizar Receipt COM arquivo
```bash
PUT /api/receipts/{id}/upload

Request:
- userId (string)
- status (string)
- originalKey (string)
- processedKey (string)
- file (multipart file) - Novo arquivo
```

**Exemplo com cURL:**
```bash
curl -X PUT "http://localhost:8080/api/receipts/550e8400-e29b-41d4-a716-446655440000/upload" \
  -F "userId=user-123" \
  -F "status=PROCESSED" \
  -F "originalKey=key-123" \
  -F "processedKey=key-processed-123" \
  -F "file=@/path/to/new-receipt.pdf"
```

### 8. Deletar Receipt
```bash
DELETE /api/receipts/{id}

Response: 204 No Content
```

## Validações Importantes

### Tamanho máximo do arquivo (application.yaml)
Adicione as seguintes propriedades para limitar o tamanho do upload:

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
```

### Tipos de arquivo permitidos
Você pode adicionar validação no ReceiptController:

```java
private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
    "application/pdf",
    "image/jpeg",
    "image/png",
    "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
);

// Validar no método de upload
if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
    throw new IllegalArgumentException("Tipo de arquivo não permitido");
}
```

## Fluxo de Upload Recomendado

1. **Usuário seleciona arquivo** na aplicação frontend
2. **Cliente faz POST para `/api/receipts/upload`** com:
   - Dados do recebimento (userId, status, etc)
   - Arquivo (MultipartFile)
3. **Service processa:**
   - Valida o arquivo
   - Converte MultipartFile para byte[]
   - Salva Receipt com dados do arquivo no banco
4. **API retorna:** ReceiptDTO com metadados do arquivo

## Banco de Dados

Os dados do arquivo são armazenados em:
- `receipts.file_data` (LONGBLOB) - Dados binários
- `receipts.file_name` (VARCHAR) - Nome do arquivo
- `receipts.file_content_type` (VARCHAR) - Tipo MIME
- `receipts.file_size` (BIGINT) - Tamanho em bytes

## Integração com AWS S3 (Futuro)

Para melhor performance em produção, considere:

```java
// Salvar arquivo em S3 em vez de banco de dados
String s3Key = "receipts/" + receipt.getId() + "/" + file.getOriginalFilename();
s3Client.putObject(s3Key, file.getInputStream());
receipt.setOriginalKey(s3Key);
```

Isso reduz o tamanho do banco e melhora performance.

