# Desenvoolvendo Sistema Bancário
## Criando as Classes:

### Main
>- Esta é a classe principal do sistema. Ela possui ArrayList de objetos
  Pessoa e objetos da classe Banco. É nessa classe que temos
  o menu que nos permite criar os bancos e as pessoas. Note que uma pessoa
  só se torna um cliente de uma agência a partir do momento que ela é associada
  a uma conta bancária.
  
### Banco
>- Esta é a classe Banco. A classe Sistema possui uma ArrayList de bancos
  e cada banco possui uma ArrayList de agências.
  
### Agencia
>- Esta é a classe Agência. Uma agência pertence a um banco somente, e possui
  uma ArrayList de objetos da classe Conta. Lembre-se de uma Conta pertece a
  somente uma agência, e uma agência pode ter inúmeras contas.
  
### Pessoa
>- Esta é a classe Pessoa. Uma pessoa pode estar associada a uma ou mais
  contas bancárias.
  
### Conta
>-  Esta é a classe Conta. Ela está associada a uma única agência
  e a um único cliente. Lembre-se: um banco pode ter várias agências
  e uma agência pode ter várias contas, assim como um cliente pode ter
  várias contas.
  Uma conta pode ter várias transações, ou seja, um ArrayList de objetos da
  classe Transacao.
  
### Transacao
>- A classe Transacao registra todas as transações ocorridas nas contas
  bancárias, ou seja, os depósitos, saques e transferências. Veja que cada
  conta possui um ArrayList de objetos da classe Transacao.