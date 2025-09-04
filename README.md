# APLICATIVOS DESKTOP DESENVOLVIDOS EM JAVA
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/LICENSE)

## Sobre cada aplicação
O objetivo foi para facilitar e agilizar meu dia a dia no trabalho. O uso das aplicações são bem simples e as explicarei cada uma por partes.

NOTA: para que todos possam entender o porquê dessas aplicações, eu trabalho na PRODESAN como técnico de informática prestando suporte técnico a todos os servidores da Prefeitura de Santos por meio do sistema SAU(sistema de atendimento ao usuário). Então desenvolvi esses aplicativos para me auxiliar no desenvolvimento do meu trabalho.


### AppTerfas
Este aplicativo é para simplesmente fazer os registros dos atendimentos que prestei.

OBS.: não foquei no CSS, mas sim apenas na sua funcionalidade. No entanto, se alguém se interessar na aplicação, o código estará disponível para alterar a seu próprio modo.

Esta é a tela de fazer o registro do atendimento.

Tarefa: Número da tarefa ou Ordem de Serviço.

Solicitante: Nome da pessoa que fez o pedido.

Destino: Uma descrição bem objetiva do que foi feito ou para qual departamento a tarefa foi encaminhada.
![Cadastrar1](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/assets/tela_cadastro.png)

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Como o nome já diz, esta tela é para fazer as consultas das tarefas que já foram atendidas. Podendo ser feito pelos atributos: Código da tarefa ou Ordem de serviço, Data que a tarefa foi atendida ou Nome do solicitante(aqui se quiser pode pesquisar apenas pelo primeiro nome, caso não saiba o nome completo).

![Consulta2](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/assets/tela_consulta.png)

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Por fim, a tela que mostra toda a conversa feito entre o técnico e o solicitante.

Para melhor entendimento, aqui na empresa às vezes conversamos com o solicitante via chat(desenvolvido pela própria equipe da TI) e por questão de posteriores consultas, a conversa é salva como um log em 3 locais diferentes e 1 deles é na máquina do técnico. E por questão de agilidade no acesso à informação, desenvolvi esta tela.

![Historico3](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/assets/tela_historico_chat.png)

Sua funcionalidade é bem simples. 

No campo "Digite o código da tarefa" ela recebe a informação neste formato: 12345/2025; (antes da barra é o número da tarefa e depois da barra é o ano)

Depois clique no botão VER que o campo conversa será preenchido com toda a conversa feita no chat entre ambos.
