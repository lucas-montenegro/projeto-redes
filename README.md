# Projeto Redes de Computadores

## Funcionalidades
A aplicação possui as seguintes funcionalidades:
* Adicionar um evento com prioridade
* Alterar prioridade de um evento
* Remover um evento
* Listar os eventos adicionados pela ordem de prioridade

#### ADD    (ADD#PARÂMETRO1#PARÂMETRO2)
Adiciona uma tarefa com uma prioridade na lista de tarefas.  
**Formatação dos parâmetros:**
* PARÂMETRO1: String que equivale ao nome da tarefa a ser adicionada;
* PARÂMETRO2: número inteiro que representa a prioridade da tarefa.

#### CHANGE  (CHANGE#PARÂMETRO1#PARÂMETRO2)
Altera a prioridade de uma tarefa existente na lista de tarefas.
**Formatação dos parâmetros:**
* PARÂMETRO1: número inteiro equivalente a posição da tarefa escolhida. Ver o número da posição de cada tarefa com o comando SHOW;
* PARÂMETRO2: inteiro que representa a nova prioridade da tarefa escolhida.

#### REMOVE (REMOVE#PARÂMETRO1)
Remove uma tarefa existente
**Formatação da mensagem:**
* PARÂMETRO1: número inteiro equivalente a posição da tarefa escolhida. Ver o número da posição de cada tarefa com o comando SHOW.

#### SHOW (SHOW)
Lista as tarefas, ordenando-as de forma decrescente de acordo com a prioridade.

#### QUIT (QUIT)
Encerra a conexão do cliente com o servidor.

## Execução

Foi utilizado o IntelliJ IDEA para construir e executar o projeto. Segue nas linhas abaixo, os comandos para executar o cliente e o servidor.

### Cliente
Para executar a aplicação cliente vá para a pasta *out/production/projeto-redes* e execute o seguinte comando:
```
java Client 
```

### Servidor
Para executar a aplicação servidor vá para a pasta *out/production/projeto-redes* e execute o seguinte comando:
```
java Server
```
## Extensões necessárias
* Javafx SDK
