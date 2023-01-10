# API 
> O objetivo deste documento é identificar seus conhecimentos quanto às tecnologias utilizadas no cotidiano de desenvolvimento da equipe de Back-end na Attornatus Procuradoria Digital.

Esta análise propõe avaliar os seguintes temas:
<ul>
<li>Qualidade de código</li>
<li>Java, Spring boot</li>
<li>API REST</li>
<li>Testes</li>
</ul>

## API 

A API permite:  
<ul>
<li>Criar uma pessoa</li>
<li>Editar uma pessoa</li>
<li>Consultar uma pessoa</li>
<li>Listar pessoas</li>
<li>Criar endereço para pessoa</li>
<li>Listar endereços da pessoa</li>
<li>Poder informar qual endereço é o principal da pessoa</li>
</ul>

Uma Pessoa tem os seguintes campos:  
<ul>
<li>Nome</li>
<li>Data de nascimento</li>
<li>Endereço
<ul>
<li>Logradouro</li>
<li>CEP</li>
<li>Número</li>
<li>Cidade</li>
</ul></li>
</ul>

## Organização do Projeto
A organização do projeto é apresentada abaixo.

```
+-- src/                    # Codigos da API Rest
+-- POSTMAN/                # Configuração do postman para teste na plataforma 
```  

## Desenvolvimento
### IDE
A IDE recomendada para o desenvolvimento dos microsserviços é o [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/), pois tem excelente integração com linguagens que rodam na Máquina virtual Java além de ser uma das referências de mercado de IDEs.
