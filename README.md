# Gestão de livraria virtual

Spring boot REST API para gestão de uma livraria virtual

API implementada como parte da disciplina de Arquitetura de Backend e Microsserviços do curso de pós-graduação em Arquitetura de Software Distribuído da PUC Minas.

## Autor

* Jonas Rocha Castanheira
* Matrícula: 109997

## Instalação
### 1 - Composer.json
Adicione uma key do tipo **repositories** ao `composer.json` do seu projeto com a seguinte configuração:
~~~
"repositories": [{
        "type": "vcs",
        "url": "https://gitlab.com/gafit/zoop-helper.git"
    }
]
~~~

### 2 - Composer require
Use o composer para instalar o package e atualizar automaticamente o `composer.json`, utilizando o seguinte comando:

~~~
composer require gafit/zoop-helper
~~~

### 3 - Atualizar configurações do Laravel
Atualize as configurações da aplicação Laravel para registrar o package adicionando a seguinte linha de comando à seção **providers** do arquivo `config/app.php`:
~~~
'providers' => [
    //...
    Zoop\ZoopServiceProvider::class,
    //...
],
~~~

### 3 - Atualize as API Keys da Zoop
Adicione as chaves da API ao arquivo `zoop-helper/src/resources/config/config.php`:
~~~
'defaults'  => [
    //...
    'publishable_key'   => 'YOUR_PUBLISHABLE_KEY',
    'marketplace_id'    => 'YOUR_MARKETPLACE_ID',
    //...
]
~~~

## Termos e dúvidas comuns

### 1 - Transação cartão não-presente
São transações on-line comuns, nas quais o cartão de crédito do comprador não está fisicamente presente.

### 2 - Tokenização
Se refere ao ato de guardar objetos que contêm dados sensíveis (como contas bancárias e cartões de crédito) dentro da Zoop enquanto nosso sistema usa apenas o ID do token gerado.
 
### 3 - Associação de token a usuário
É possível associar um token de cartão de crédito ou conta bancária a um comprador / vendedor na Zoop. Quando isso é feito, o token em si deixa de ter validade na Zoop, e é necessário usar o ID do objeto cartão (CreditCard) ou conta bancária (BankAccount) ao fazer operações com esses objetos na Zoop, por exemplo ao excluir um cartão de crédito.

## Exemplos de uso

### 1 - Criação de comprador
**Em seu model de usuário:**
```php
namespace App;
 
use Gafit\Zoop\src\Facades\ZoopBuyers;
 
class User extends Model {

    ...
    
    $v_Buyer = ZoopBuyers::create([
        "first_name" => "Ricardo",
        "last_name" => "Petraglia"
    ]);
}
```

### 2 - Tokenização de cartão de crédito
**Em seu model de cartões de crédito:**
```php
namespace App;
 
use Gafit\Zoop\src\Facades\ZoopTokens;
 
class CreditCard extends Model {

    ...
    
    $v_CardToken = ZoopTokens::tokenizeCard([
       "holder_name" => "Makeda Swasey",
       "expiration_month" => "12",
       "expiration_year" => "2015",
       "security_code" => "373",
       "card_number" => "4532395075641483",
    ]);
}
```

### 3 - Criação de vendedor do tipo Pessoa Física (individual)
**Em seu model de usuário ou vendedor:**
```php
namespace App\Http\Controllers;
 
use Gafit\Zoop\src\Facades\ZoopSellers;
 
class User extends Model {

    ...
       
    $v_Seller = ZoopSellers::create([
        'first_name' => 'Ricardo',
        'last_name' => "Petraglia",
        'email' => "ricardo.petraglia@gafit.com.br",
        'phone_number' => "+12195465432",
        'ssn_last4_digits' => "7551",
        'birthdate' => "1983-09-11",
        'website' => "http://gafit.com.br",
        'facebook' => "https://www.facebook.com/gafit"
    ]);
}
```

### 4 - Transação do tipo "cartão não-presente"
**Em seu model de transações:**
```php
namespace App;
 
use Gafit\Zoop\src\Facades\ZoopChargeCNP;
 
class Transaction extends Model {

    ...
    
    $v_Transaction = ZoopChargesCNP::create([
        'currency' => 'BRL',
        'amount' => '100',
        'payment_type' => 'credit',
        'description' => 'Venda de teste, somente!',
        'statement_descriptor' => 'Descrição de testes',
        'installment_plan[mode]' => 'interest_free',
        'installment_plan[number_installments]' => '1',
        'on_behalf_of' => 'bb2a51f1c22a4c30b6bf6819be87ac52',   //Seller ID
        'customer' => 'bb2a51f1c22a4c30b6bf6819be87ac52'        //Buyer ID
    ]);
}
```

## Funções úteis

### 1 - Criação completa de vendedor
Cria um vendedor, posta seus documentos de comprovação, cria, tokeniza e associa uma conta bancária para recebimentos e muda sua política de recebimentos para "manual", evitando que o vendedor faça saques de sua carteira da Zoop por conta própria. A função recebe como último parâmetros:
* **$p_Data:** Array de dados do vendedor;
* **$p_Documents:** Array de FILEs com comprovantes;
* **$p_BankAccount:** Array com dados da conta bancária;
* **$p_UserModel:** O modelo (Model::class)de usuário usado para salvar os dados do vendedor.
```php
public static function completeSellerCreation($p_Data, $p_Documents, $p_BankAccount, $p_SellerType, $p_UserModel) {
    //Configurando informações adicionais do vendedor
    $p_UserModel::processSellerData($p_Data, $p_BankAccount, $p_SellerType);
    
    //Cadastro do vendedor na Zoop
    $v_SellerOnZoop = self::postSeller($p_Data, $p_SellerType);
    
    //Processamento e adição de dados de vendedor ao sistema / banco de dados
    $p_UserModel::postSellerData($v_SellerOnZoop);
    
    //Cadastro dos documentos de comprovação de identidade, residência e atividade do vendedor
    self::createSellerDocuments($p_Documents, $v_SellerOnZoop['id']);
    
    //Tokenização e associação de conta bancária ao vendedor (para recebimentos)
    self::tokenizeAndLinkBankAccountToSeller($p_BankAccount, $v_SellerOnZoop['id']);
    
    //Configurando vendedor para que ele não possa fazer retirada de dinheiro da Zoop para sua conta bancária
    self::postReceivingPolicy($v_SellerOnZoop['id'], [
        'transfer_enabled' => false,
        'minimum_transfer_value' => 1
    ]);
}
```

### 2 - Processamento de webhooks
Recebe dados de webhooks da Zoop, extrai o tipo do evento e processa webhook na função interna do modelo usado para salvar os webhooks no banco. A função recebe como parâmetros:
* **$p_WebhookData:** Dados enviados pela Zoop;
* **$p_WebhookModel:** O modelo (Model::class) da tabela de webhooks do gateway no banco.
```php
public static function postNotification($p_WebhookData, $p_WebhookModel) {
    //Salvando dados do webhook no banco de dados (nesse caso, essa operação é a primeira a ser feita
    // para garantir que os dados sejam salvos no banco mesmo em caso de um webhook inválido)
    $p_WebhookModel::post($p_WebhookData);
    
    //Pegando tipo de evento do webhook
    $v_EventType = self::getNotificationType($p_WebhookData['type']);
    
    //Processamento do webhook no modelo interno
    $p_WebhookModel::processNotification($v_EventType, $p_WebhookData, $p_WebhookData['id']);
}
```

## ZOOP API Reference

[https://docs.zoop.co/v1/reference](https://docs.zoop.co/v1/reference)

## ZOOP Docs

[https://docs.zoop.co/docs](https://docs.zoop.co/docs)
