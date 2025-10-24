# 🛰 Publicador Multiplataforma (Adapter + Factory)

Este projeto demonstra como publicar conteúdo em diferentes redes sociais (Twitter, Instagram, etc.) usando uma arquitetura flexível com os padrões de projeto **Adapter** e **Factory Method** em Java.

A ideia é: o sistema não precisa saber "como" cada rede social autentica ou publica.
Ele só conversa com uma interface genérica (`AdapterRedes`). Cada rede implementa essa interface do seu jeito.

---

## 🚀 Objetivos

* Padronizar autenticação e publicação em várias redes sociais.
* Permitir troca de rede social em tempo de execução (sem precisar reescrever código).
* Facilitar inclusão de novas redes sociais no futuro (ex: TikTok, LinkedIn).

---

## 🧠 Padrões de Projeto Usados

### 1. Adapter (`AdapterRedes`)

Serve para "traduzir" a comunicação genérica do sistema para o formato específico de cada rede social.

* `TwitterAdapter` sabe publicar no Twitter.
* `InstagramAdapter` sabe publicar no Instagram.
* As duas classes implementam a mesma interface `AdapterRedes`.

Assim, o resto do sistema não precisa saber detalhes de API de cada rede.

```java
public interface AdapterRedes {
    void autenticar();
    void publicar(Conteudo conteudo);
}
```

---

### 2. Factory Method (`FactoryMidiaSocial`)

Garante que cada rede social tenha sua própria fábrica para criar o adaptador correto.

* `TwitterAdapterFactory` cria um `TwitterAdapter`
* `InstagramAdapterFactory` cria um `InstagramAdapter`

Isso evita `new TwitterAdapter()` espalhado pelo código.
Quem precisar de um adaptador pede pra fábrica.

```java
public interface FactoryMidiaSocial {
    AdapterRedes criarAdaptador();
}
```

---

## 📦 Estrutura das Classes

* `Conteudo`
  Representa o que será publicado (título e descrição).

* `AdapterRedes`
  Interface genérica que define `autenticar()` e `publicar()`.

* `TwitterAdapter` / `InstagramAdapter`
  Implementações concretas de publicação para cada rede.

* `FactoryMidiaSocial`
  Interface de fábrica.

* `TwitterAdapterFactory` / `InstagramAdapterFactory`
  Fábricas concretas de cada adaptador.

* `GerenciadorMidiaSocial`
  Camada que orquestra tudo. Ele recebe um adaptador e chama os métodos de autenticar/publicar sem saber detalhes da rede.

* `Main`
  Classe de exemplo que mostra o uso prático.

---

## 🧩 Fluxo em tempo de execução

1. Criamos um conteúdo:

```java
Conteudo conteudo = new Conteudo(
    "Teste supremo!",
    "Realizando um teste do padrão Adapter."
);
```

2. Criamos uma fábrica da rede desejada (Twitter):

```java
FactoryMidiaSocial Twitter = new TwitterAdapterFactory();
AdapterRedes adapter = Twitter.criarAdaptador();
```

3. Passamos esse adaptador para o `GerenciadorMidiaSocial`:

```java
GerenciadorMidiaSocial gerenciador = new GerenciadorMidiaSocial(adapter);
gerenciador.autenticar();
gerenciador.publicar(conteudo);
```

4. No meio do sistema, podemos trocar para outra rede (Instagram) **sem recriar o gerenciador**:

```java
FactoryMidiaSocial Instagram = new InstagramAdapterFactory();
AdapterRedes adapter2 = Instagram.criarAdaptador();

gerenciador.alterarAdaptador(adapter2);
gerenciador.autenticar();
gerenciador.publicar(conteudo);
```

Saída esperada (exemplo simplificado):

```text
Autenticando...
Publicando no Twitter: Teste supremo! - Realizando um teste do padrão Adapter.

Autenticando...
Publicando no Instagram: Teste supremo! - Realizando um teste do padrão Adapter.
```

---

## 🔌 Como rodar

1. Garanta que você tem Java (8+) instalado.
2. Coloque todas as classes no mesmo projeto (ou no mesmo pacote).
3. Rode a classe `Main`.

Exemplo usando terminal:

```bash
javac *.java
java Main
```

---

## ➕ Como adicionar uma nova rede social

Exemplo: você quer adicionar `LinkedIn`.

1. Crie um novo adapter:

```java
public class LinkedInAdapter implements AdapterRedes {
    @Override
    public void autenticar() {
        System.out.println("Autenticando no LinkedIn...");
    }

    @Override
    public void publicar(Conteudo conteudo) {
        System.out.println("Publicando no LinkedIn: " 
            + conteudo.getTitulo() + " - " + conteudo.getDescricao());
    }
}
```

2. Crie a fábrica dessa rede:

```java
public class LinkedInAdapterFactory implements FactoryMidiaSocial {
    @Override
    public AdapterRedes criarAdaptador() {
        return new LinkedInAdapter();
    }
}
```

3. Use no `Main`:

```java
FactoryMidiaSocial linkedInFactory = new LinkedInAdapterFactory();
gerenciador.alterarAdaptador(linkedInFactory.criarAdaptador());
gerenciador.publicar(conteudo);
```

Nenhuma mudança é necessária no `GerenciadorMidiaSocial`.
Isso mostra que a arquitetura está bem desacoplada.

---

## 🧱 Benefícios da Arquitetura

* **Desacoplamento**: O core do sistema não conhece detalhes de API de cada rede.
* **Extensibilidade**: Nova rede social = novo Adapter + nova Factory.
* **Reuso**: O mesmo `GerenciadorMidiaSocial` serve para todas as redes.
* **Baixo impacto em mudanças**: Se o Instagram mudar regras de autenticação, você altera só `InstagramAdapter`.

---

## 👨‍💻 Autor

Trabalho acadêmico demonstrando padrões de projeto (Adapter + Factory Method) aplicados na integração com redes sociais.
