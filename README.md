# Sistema de Reservas de Hotel

Este projeto implementa um sistema de reservas de hotel utilizando Java RMI (Remote Method Invocation), permitindo que os usuários gerenciem hotéis e reservas de quartos de maneira remota. O sistema é composto por uma classe servidor (), que gerencia todas as operações do sistema, e uma interface () que define os métodos disponíveis para interação remota.

## Recursos

- Adicionar e Remover Hotéis: Permite que o administrador adicione ou remova hotéis do sistema.
- Consultar Disponibilidade: Usuários podem consultar a disponibilidade de quartos em um hotel específico.
- Gerenciar Reservas: Inclui funcionalidades para adicionar, modificar e remover reservas de quartos.

## Como Executar

Para executar o servidor do sistema de reservas de hotel, siga estas etapas:

1. Certifique-se de ter o Java Runtime Environment (JRE) instalado em sua máquina.
2. Compile o código-fonte usando um compilador Java (javac).
3. Execute o servidor RMI utilizando o comando.
4. Compilar os arquivos Java
```bash
javac *.java
```
5. Executar o servidor
```bash
java HotelServer
```
6. Executar o client
```bash

java Client
```

## Estrutura do Projeto

## O projeto é composto pelos seguintes arquivos principais:

- : Contém a lógica do servidor, incluindo a inicialização do serviço RMI, e os métodos para gerenciamento de hotéis e reservas.
- : Define a interface que o servidor implementa, declarando os métodos que podem ser invocados remotamente.

## Tecnologias Utilizadas

- Java RMI: Utilizado para criar uma aplicação distribuída que permite a execução de chamadas remotas, como se fossem chamadas locais.

## Exemplo de Uso

Após iniciar o servidor, o sistema aguardará por comandos do administrador. Aqui estão alguns exemplos de como interagir com o sistema:

1. Adicionar um Hotel:
    - Escolha a opção e siga as instruções para adicionar um novo hotel ao sistema.
2. Consultar Disponibilidade:
    - Escolha a opção e forneça o nome do hotel para consultar a disponibilidade de quartos.
3. Modificar uma Reserva:
    - Escolha a opção , informe o nome do hotel e os números dos quartos atual e desejado para modificar uma reserva existente.
  

