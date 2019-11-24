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


##### *Acessar jboss-cli*
Execute jboss-cli.bat --connect na pasta bin onde se encontra o seu WildFly.


##### Database no MySQL
```
CREATE USER 'agendamento'@'localhost' IDENTIFIED BY 'agendamento';
GRANT ALL PRIVILEGES ON * . * TO 'agendamento'@'localhost';
CREATE DATABASE agendamentobd;
```

##### Datasource no WildFly

Criar manualmente no arquivo standalone.xml:

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

Criar usando o jboss-cli:

```
module add --name=com.mysql --resources=/path/to/mysql-connector-java-8.0.15.jar --dependencies=javax.api,javax.transaction.api

```

```
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
```

```
data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd --user-name=SEU-USUARIO --password=SUA-SENHA --min-pool-size=10 --max-pool-size=20

```

##### JavaMail no WildFly

```
/subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)

```

```
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)
```

```
/subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=bc82647d48b758, password=6320632ea13bd1, tls=true)

```

##### JMS no WildFly

```
jms-queue add --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue

```

Verificar se as mensagens foram enviadas para a fila JMS:

```
jms-queue list-messages --queue-address=EmailQueue
```

Verificar se os emails foram enviados aos respectivos destinatários:

```
/subsystem=messaging-activemq/server=default/jms-queue=EmailQueue:list-messages
```

Verificar se as mensagens foram enviadas para a fila de erro caso tenha ocorrido algum ao tentar enviar o email:

```
/subsystem=messaging-activemq/server=default/jms-queue=DLQ:list-messages
```

##### Console Swagger
[http://localhost:8080/doc/#/](http://localhost:8080/doc/#/)

##### Aplicação front-end
[http://localhost:8080/](http://localhost:8080/)

 