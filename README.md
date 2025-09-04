# APLICATIVOS DESKTOP DESENVOLVIDOS EM JAVA
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/LICENSE)

## Sobre cada aplicação
O objetivo foi para facilitar e agilizar meu dia a dia no trabalho. O uso das aplicações são bem simples e as explicarei cada uma por partes.

NOTA: para que todos possam entender o porquê dessas aplicações, eu trabalho na PRODESAN como técnico de informática prestando suporte técnico (telefônico e remoto) a todos os servidores da Prefeitura de Santos por meio do sistema SAU(sistema de atendimento ao usuário). Então desenvolvi esses aplicativos para me auxiliar no desenvolvimento do meu trabalho.


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

*****************************************************************************************************************************************************************************************************************

### Instaladores
Mais uma aplicação para auxílio no dia a dia pelo motivo de fazer com muita frequência essas instalações.

OBS.: É possível fazer isso de forma manual(entrando nas pastas da rede, executando e etc), mas como cada parte fica em um lugar diferente, então apenas centralizei todo processo e com apenas um clique.

Como expliquei antes, o objetivo é bem simples e objetivo INSTALAR A APLICAÇÃO; Contudo, para que esta seja instalada por completo é preciso instalar partes dela e em cada aba está contido tudo isso, conforme as imagens abaixo.

![instaladores1](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/assets/instaladores_pg1.png)

![instaladores2](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/assets/instaladores_pg2.png)

*****************************************************************************************************************************************************************************************************************

## Tecnologias Usadas
JAVA (junto com a biblioteca FX);

Banco de dados: MySQL (XAMPP instalado localmente)

*****************************************************************************************************************************************************************************************************************

## Como executar as aplicações
OBS.: toda a estrutura da pasta deverá estar dentro da raiz do sistema para que possa funcionar( C:/ )

1º - Abra o código fonte na IDE de sua prefêrencia;

2º - Exporte o projeto como "Runnable JAR";

3º - Crie um estrutura de pastas igual a esta:

![estrutura_pastas](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/assets/executando_aplica%C3%A7%C3%A3o.png)

Quando projeto for exportado, você terá algumas pastas ja prontas como a lib, sdk e o executável java(o qual você não deve executá-lo, pois ele não funcionará). A pasta jdk é o kit de desenvolvimento(neste caso estou usando o zulu17.52.17-ca-jdk17.0.12-win_x64)

A imagem não de tanta importância, pois será usada como ícone no canto esquerdo suporior da janela.

Agora o último arquivo( que é um bat ), este sim é o executável do programa e deverá ter essas linhas de código:

![codigo_bat](https://github.com/code9-crypto/app-tarefas-PMS/blob/main/assets/codigo_bat_java.png)

Bem simple e breve do que está acontecendo aqui. O programa vai buscar o JDK, SDK e executável(JAVA - classe principal) no caminho descrito para executá-lo
