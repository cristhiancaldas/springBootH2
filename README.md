
# Microservicio User

Microservicio usano utilizando JPA, H2 , LOMBOk


## Authors

- [@cristhiancaldas](https://www.github.com/cristhiancaldas)


## Badges

Add badges from somewhere like: [shields.io](https://shields.io/)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)

[![MIT License](https://github.com/cristhiancaldas/springBootH2/actions/workflows/maven.yml/badge.svg)](https://choosealicense.com/licenses/mit/)

## API Reference

#### Get all items

```http
  GET /user/v1
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
|  |  |  |

#### Get item

```http
  GET /user/v1/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### Post item

```http
  Post /user/v1/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `nombre`  | `string` | **Required**. Name of item to fetch |
| `lastName`| `string` | **Required**. lastName of item fetch |
| `age`     | `string` | **Required**. Age of item to fetch |

#### 

## License

[MIT](https://choosealicense.com/licenses/mit/)

