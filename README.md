# web-monitor

前端监控系统。

## 环境要求

- SQLite3
- JDK 8
- 端口 9101、9102 未被使用

## 部署项目

克隆项目到本地

```
git clone https://github.com/senntyou/web-monitor.git --depth=1
```

1. 在服务器上，找个合适的地方创建 `web-monitor` 目录
2. 把本地项目中 `bin/run.sh` 上传到服务器 `web-monitor` 目录
3. 把本地项目中 `sql` 目录上传到服务器 `web-monitor` 目录
4. 复制本地项目中 `flyway.conf.example` 为 `flyway-prod.conf`，修改 `flyway.url` 配置，并上传到服务器 `web-monitor` 目录
5. 下载最新的 `webmonitor-x.x.x.jar` 文件，并上传到服务器 `web-monitor/libs` 目录

```
- web-monitor/
  - run.sh              # 可以按实际需要更改 SERVER_ENV 变量的值
  - sql/                # 数据库结构定义
  - flyway-prod.conf    # 数据库链接配置（需更新 `flyway.url` 配置）
  - libs/　
    - x.x.x.jar
```

创建数据库及结构（在服务器上）

```
cd path/to/web-monitor

sqlite3 db.sqlite

flyway migrate -configFiles=flyway-prod.conf
```

把 `pro-main/src/main/resources/application-prod.yml` 上传到服务器 `~/.webmonitor` 目录，并修改  `spring.datasource.url` 配置

```
~/.webmonitor
  - application-prod.yml
```

运行项目

```
sh run.sh start        # 运行程序
sh run.sh stop         # 停止程序
sh run.sh restart      # 重启程序
sh run.sh status       # 查看程序状态
```

测试是否启动成功

```
curl http://localhost:9101

# {"code":200,"message":"Operation Succeeded","data":"Ok"}
# 如果返回上述的结果，表示启动成功
```

## 前端 JS SDK 使用

请参考 [web-monitor-sdk](https://github.com/senntyou/web-monitor-sdk).
