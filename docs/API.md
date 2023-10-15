# Prophet Salary 接口文档

要求：
- 根路由是`/api`
- 接口只用 `GET`、`POST` 方法，其中`GET`方法使用`@RequestParam`接收参数，`POST`方法使用`@RequestBody`接收整个参数对象再从其中获取
- 业务错误抛出`400`错误，并在`message`字段中携带错误信息
- 鉴权方式使用`JWT`，并作为参数而不放在`header`中携带
- 日期格式一律使用`YYYY-MM-DD hh:mm:ss`

## 登录鉴权

```typescript
// POST /auth/login 
interface Request {
  id: string;
  password: string;
  remember: boolean;
}

interface Response {
  id: string;
  username: string;
  role: 'employee' | 'commission' | 'payroll';
  jwt: string;
}
```

## 创建管理报告
```typescript
// POST /admin_report/create
interface Request {
  type: 'duration' | 'salary';
  start_time: string;
  end_time: string;
  employee_name: string;
}

interface Response {
  data: EmployeeDurationReport[] | EmployeeSalaryReport[];
}
```

## 创建员工报告
```typescript
// POST /employee_report/create
interface Request {
  type: 'duration' | 'proj_duration' | 'vacation' | 'salary';
  project_name: string;
  start_time: string;
  end_time: string;
}

interface Response {
  data: DurationReport[] | ProjectDurationReport[] | VacationReport[] | SalaryReport[];
}
```

## 维护员工信息
```typescript
// POST /employee/create
interface CreateRequest {
  type: 'salary' | 'commission' | 'wage'; // 月薪 ｜ 佣金 ｜ 时薪
  origin_id: string; // 员工名字的拼音，后端自动检测拼音是否重复，如有重复要自动加上数字
  name: string; // 员工名字
  address: string;
  socsec_id: string;
  tax_rate: number;
  other_cast: number;
  phone: string;
  hour_wage?: number;
  salary?: number;
  commission_rate?: number;
  duration_limit: number;
}

interface CreateResponse extends CreateRequest {
  id: string; // 新的 id，一般是员工拼音 + 数字：如果没有拼音就不用数字
  jwt: string;
}

// GET /employee/get
interface GetRequest {
  jwt: string;
}

interface GetResponse extends CreateRequest {
  id: string;
  payment: 'mail' | 'receive' | 'bank';
}

// POST /employee/update
interface UpdateRequest extends Partial<CreateRequest> { // Partial<T> 代表 T 上的任何 key 都是可选的
  id: string;
  payment?: 'mail' | 'receive' | 'bank';
  mail_address?: string;
  bank_name?: string;
  bank_account?: string;
}

interface UpdateResponse {}

// POST /employee/delete
interface DeleteRequest {
  id: string;
}

interface DeleteResponse {}
```

## 维护采购工单
```typescript
// POST /purchase/create
interface CreateRequest {
  phone: string;
  address: string;
  product_name: string;
  date: string;
}

interface CreateResponse {
  id: string;
  employee_id: string;
}

// GET /purchase/get 
interface GetRequest {
  id: string;
  jwt: string;
}

interface GetResponse extends CreateRequest {
  id: string;
}

// POST /purchase/update 
interface UpdateRequest extends Partial<CreateRequest> {
  id: string;
  jwt: string;
}

interface UpdateResponse {}

// POST /purchase/delete
interface DeleteRequest {
  id: string;
  jwt: string;
}

interface DeleteResponse {}
```

## 维护考勤卡
```typescript
// GET /timecard/get
interface GetRequest {
  jwt: string;
  page_index: number;
  page_size: number;
}

interface GetResponse {
  data: {
    id: string
    is_save: boolean;
    start_time: string;
    end_time: string;
    duration: number;
  }[];
}

// POST /timecard/update
interface UpdateRequest {
  id: string;
  jwt: string;
  duration: number;
}

interface UpdateResponse {}
```