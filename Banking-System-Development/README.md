# Desenvoolvendo Sistema Banc�rio
## Criando as Classes:

### Main
>- Esta � a classe principal do sistema. Ela possui ArrayList de objetos
  Pessoa e objetos da classe Banco. � nessa classe que temos
  o menu que nos permite criar os bancos e as pessoas. Note que uma pessoa
  s� se torna um cliente de uma ag�ncia a partir do momento que ela � associada
  a uma conta banc�ria.
  
### Banco
>- Esta � a classe Banco. A classe Sistema possui uma ArrayList de bancos
  e cada banco possui uma ArrayList de ag�ncias.
  
### Agencia
>- Esta � a classe Ag�ncia. Uma ag�ncia pertence a um banco somente, e possui
  uma ArrayList de objetos da classe Conta. Lembre-se de uma Conta pertece a
  somente uma ag�ncia, e uma ag�ncia pode ter in�meras contas.
  
### Pessoa
>- Esta � a classe Pessoa. Uma pessoa pode estar associada a uma ou mais
  contas banc�rias.
  
### Conta
>-  Esta � a classe Conta. Ela est� associada a uma �nica ag�ncia
  e a um �nico cliente. Lembre-se: um banco pode ter v�rias ag�ncias
  e uma ag�ncia pode ter v�rias contas, assim como um cliente pode ter
  v�rias contas.
  Uma conta pode ter v�rias transa��es, ou seja, um ArrayList de objetos da
  classe Transacao.
  
### Transacao
>- A classe Transacao registra todas as transa��es ocorridas nas contas
  banc�rias, ou seja, os dep�sitos, saques e transfer�ncias. Veja que cada
  conta possui um ArrayList de objetos da classe Transacao.