# Árvore Binária de Código Morse em Java

Este projeto implementa uma árvore binária que representa o código Morse, permitindo converter texto para código Morse e vice-versa, além de buscar, exibir e remover caracteres.



## Funcionalidades

- Buscar letras e números e mostrar seu código Morse.  
- Traduzir mensagens de **texto → código Morse**.  
- Traduzir mensagens de **código Morse → texto**.  
- Exibir a **estrutura hierárquica da árvore**.  
- Remover caracteres da árvore sem perder a estrutura.  


## Estrutura do Projeto

📁 src
└── org.example
├── Main.java # Programa principal (menu interativo)
├── ArvoreBinariaMorse.java # Lógica da árvore binária Morse
└── Node.java # Representação dos nós da árvore



## Como Executar

1. Compile os arquivos:
   ```bash
   javac -d out src/org/example/*.java
Execute o programa:

bash
Copy code
java -cp out org.example.Main


## Exemplo de Uso
Entrada:


Digite o texto para converter em código Morse:
SOS
Saída:
Código Morse: ... --- ...

Autor: 
Eros Felipe de Quevedo dos Santos
Projeto desenvolvido para fins acadêmicos, com foco em estruturas de dados e manipulação de árvores binárias em Java.

Licença
Uso livre para fins de estudo e aprendizado.
