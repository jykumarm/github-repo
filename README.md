# github-repo
Backend application for discovering popular
repositories on GitHub.

#### Get all items

```http
  GET /api/v1/repositories
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `date` | `string` | **Required**. matches repositories that were created before the date, YYYY-MM-DD |
| `language` | `string` | matches repositories by language|
 `pageSize` | `string` | number of entries to fetch|





## Features

- Spring caching - Caffeine
- Fault tolerance support - Resilience4j
- Includes Integration and unit test
