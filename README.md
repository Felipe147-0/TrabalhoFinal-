# Nomes:
João Pedro de Almeida Andolpho AQ3022501  <br>
Guilherme Siguli Crespo AQ3022889  <br>
Felipe Paganini de Abreu AQ3016439  <br>
Deivid Pereira Brito AQ3022749  <br>

# Nome aplicativo: Conexão Verde 

Proposta de resolução social: Revitalização arbórea urbana ou rural, com plantio de arvores
nativas da região (em que está sendo utilizado) ou frutíferas, através de doação voluntaria e 
plantio por cidadãos interessados.  <br>
O Seguinte projeto se apresenta por meio de um aplicativo que propõe facilitar o plantio de 
arvores nas cidades, aproximando viveiros, ongs ou empresas que fomentam o reflorestamento e 
possuem mudas, ou mesmo donos de sítios e chácaras que por boa vontade desejam compartilhar suas 
mudas com moradores ou voluntários que desejam plantar uma arvore, sejam em suas residências, na 
calçada, em seu quintal ou em áreas que estão degradas tais como praças, canteiros 
centrais de avenidas ou encostas de rios que sofreram algum processo de antropomorfização 
severo como desmatamento ou queimadas. O aplicativo tem uma interface simples tanto para o 
fornecedor cadastrar suas mudas quanto para o “cliente” que busca encontrar uma arvore que
melhor se adeque ao seu desejo.  <br>
Resumidamente, o aplicativo fornece de maneira simples, uma área para um “fornecedor” se cadastrar
com seus dados e assim, poder listar suas mudas seguindo padrões tais como, se a planta é nativa 
da região ou não, se é frutífera, e qual seu tamanho, além de claro, seu nome.  <br>
Em outro lado, o interessado em encontrar uma muda, se cadastra, com seus dados e após isso, 
tem acesso a uma área de busca, onde escolherá qual tipo de muda deseja, através da escolha de 
tamanho, e tipo de muda (nativa ou frutífera), após isso será listado as mudas cadastradas no sistema 
por seus respectivos fornecedores. Após a escolha, o interessado recebera uma lista de arvores possíveis 
e então os possíveis viveiros que as possuem e seus dados de contato.  <br>
O aplicativo também conta com uma área de educação, onde é possível ver notícias cadastradas sobre ecologia ou ambientalismo.  <br>

# Resumo das funcionalidades
### Cadastro de usuários:  <br>
Os usuários podem se registrar no sistema fornecendo nome, e-mail e senha - além do tipo de usuário (cliente ou fornecedor). <br>
### Login:  <br>
Um usuário que possui cadastro no aplicativo pode acessar sua conta e as funcionalidades disponibilizadas a partir do login. <br>
### Cadastro de plantas:  <br>
O usuário fornecedor cadastrado pode cadastrar mudas de plantas que serão disponibilizadas para requisição por usuários cliente. <br>
### Estatísticas (usuário-fornecedor):  <br>
O usuário fornecedor quando logado consegue consultar todas as mudas que o mesmo cadastrou e disponibilizou na plataforma - informações como nome, espécie, tamanho e quantidade são exibidas ao usuário. <br>
### Requisição de plantas:  <br>
O usuário cliente consegue requisitar mudas de plantas de usuários fornecedores. <br>
### Estatísticas (usuário-cliente):  <br>
O usuário cliente é capaz de consultar todas as mudas que o mesmo requisitou - informações como nome, espécie, tamanho e quantidade são exibidas ao usuário. <br>
### Saiba mais! <br>
Qualquer pessoa (mesmo sem login ou cadastro) consegue acessar informações sobre o projeto Conexão Verde e notícias ao redor do tema através da tela inicial. <br>

# Tecnologias utilizadas
- Padrão room e implementação do SQLite para persistência de dados;
- Padrão de projeto MVVM;
- Componente Spinner para design e visual mais agradáveis;
- RecyclerView e adapters - utilizado nas listagens de dados;
- Componente dialog;
- DataStore - armazenamento de preferências de usuário (login e senha);

# Como executar este aplicativo?
Nosso time de desenvolvimento disponibilizou um .apk neste repositório para que você possa instalar ele em seu dispositivo android! <br>
Vale ressaltar que este .apk não funciona em dispositivos Apple. <br>
Caso prefira, você pode fazer um clone deste projeto e rodar via emulador no ambiente de desenvolvimento do Android Studio! <br>


