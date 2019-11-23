## Agendamento Email API

##### *Tecnologias usadas*
- Java
- EBJ
- Hibernate
- Jersey
- Swagger
- MySQL
- WildFly

##### *Necessário ter instalado*
- Java 1.8
- MySQL 8.0
- WildFly 15


##### Datasource
```
<datasource jta="true"
	jndi-name="java:jboss/datasources/AgendamentoDS" pool-name="AgendamentoDS"
	enabled="true" use-java-context="true" use-ccm="true">
	<connection-url>jdbc:mysql://localhost:3306/agendamentobd?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC</connection-url>
	<driver>mysql</driver>
	<transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
	<pool>
		<min-pool-size>10</min-pool-size>
		<max-pool-size>100</max-pool-size>
		<prefill>true</prefill>
	</pool>
	<security>
		<user-name>agendamento</user-name>
		<password>agendamento</password>
	</security>
	<statement>
		<prepared-statement-cache-size>32</prepared-statement-cache-size>
		<share-prepared-statements>true</share-prepared-statements>
	</statement>
</datasource>
```

```
<driver name="mysql" module="com.mysql">
	<xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
</driver>
```

##### Database
```
CREATE USER 'agendamento'@'localhost' IDENTIFIED BY 'agendamento';
GRANT ALL PRIVILEGES ON * . * TO 'agendamento'@'localhost';
CREATE DATABASE agendamentobd;
```

##### JavaMail no WildFly
Execute jboss-cli.bat --connect na pasta bin onde se encontra o seu WildFly, estando dentro do jboss-cli execute os seguintes comandos:

```
/subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)

```

```
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)
```

```
/subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=bc82647d48b758, password=6320632ea13bd1, tls=true)

```

##### Console Swagger
[http://localhost:8080/doc/#/](http://localhost:8080/doc/#/)

##### Aplicação front-end
[http://localhost:8080/](http://localhost:8080/)

 