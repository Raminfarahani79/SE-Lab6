# MiniJava
Mini-Java is a subset of Java. MiniJava compiler implement a compiler for the Mini-java
programming language.

# Rules of MiniJava
```
Goal --> Source EOF
Source --> ClassDeclarations MainClass
MainClass --> class Identifier { public static void main() { VarDeclarations Statements}}
ClassDeclarations --> ClassDeclaration ClassDeclarations | lambda
ClassDeclaration --> class Identifier Extension { FieldDeclarations MethodDeclarations }
Extension --> extends Identifier | lambda
FieldDeclarations --> FieldDeclaration FieldDeclarations | lambda
FieldDeclaration --> static Type Identifier ;
VarDeclarations --> VarDeclaration VarDeclarations | lambda
VarDeclaration --> Type Identifier ;
MethodDeclarations --> MethodDeclaration MethodDeclarations | lambda
MethodDeclaration --> public static Type Identifier ( Parameters ) { VarDeclarations Statements return GenExpression ; }
Parameters --> Type Identifier Parameter | lambda
Parameter --> , Type Identifier Parameter | lambda
Type --> boolean | int
Statements --> Statements Statement | lambda
Statement --> { Statements } | if ( GenExpression ) Statement else Statement | while ( GenExpression ) Statement | System.out.println ( GenExpression ) ; | Identifier = GenExpression ;
GenExpression --> Expression | RelExpression
Expression --> Expression + Term | Expression - Term | Term
Term --> Term * Factor | Factor
Factor --> ( Expression ) | Identifier | Identifier . Identifier | Identifier . Identifier ( Arguments ) | true | false | Integer
RelExpression --> RelExpression && RelTerm | RelTerm
RelTerm --> Expression == Expression | Expression < Expression
Arguments --> GenExpression Argument | lambda
Argument --> , GenExpression Argument | lambda
Identifier --> <IDENTIFIER_LITERAL>
Integer --> <INTEGER_LITERAL>
```


فایل اول:
# `parser`
## الگوی facade:

کلاس `Parser` در سازنده خود کار زیادی انجام می‌دهد. می‌توان یک کلاس `ParserInitializer` ایجاد کرد که وظیفه راه‌اندازی کلاس `Parser` را بر عهده بگیرد. به این ترتیب، کلاس `Parser` فقط نیاز دارد با کلاس `ParserInitializer` ارتباط برقرار کند، که به عنوان یک نمایش برای فرآیند راه‌اندازی عمل می‌کند.

## الگوی State/Strategy Pattern:

متد `startParse` شامل یک عبارت `switch` است که رفتار متد را بر اساس `currentAction.action` تغییر می‌دهد. این یک نامزد عالی برای الگوی وضعیت یا استراتژی است. می‌توان یک رابط `ActionStrategy` با یک متد `executeAction()` ایجاد کرد. سپس، برای هر مورد در عبارت `switch،` یک کلاس ایجاد می‌کنیم که `ActionStrategy` را پیاده‌سازی می‌کند.


## الگوی Separate Query From Modifier:

متد `startParse` هم وضعیت شیء `Parser `را تغییر می‌دهد و هم یک نتیجه را برمی‌گرداند. ما می‌توان این دو وظیفه را در متدهای مختلف جدا کرد.


## دیگر الگوها:
الگوی Extract Method:

متد `startParse` بسیار طولانی است. می‌توان بخش‌هایی از آن را در متدهای جداگانه استخراج کرد تا خوانایی و قابلیت نگهداری را بهبود بخشد.



فایل دوم:
# `CodeGenerator`
## الگوی State/Strategy Pattern:

می‌توان از یک نقشه برای جایگزینی عبارت `switch` استفاده کرد، که یک شکل از الگوی استراتژی است.


## الگوی Separate Query From Modifier:



متد pid هم وضعیت `symbolStack` را تغییر می‌دهد و هم `symbolTable` را پرس و جو می‌کند. می‌توان این دو را در دو متد جداگانه جدا کرد.

## دیگر الگوها:

الگوی Replace Nested Conditional with Guard Clauses:

عبارت `if` می‌تواند با یک شرط محافظ جایگزین شود تا کد قابل خواندن‌تر شود.

الگوی Replace Exception with Test:

به جای گرفتن یک استثنا، می‌توان بررسی کرد که آیا نماد در `symbolTable` قبل از دسترسی به آن وجود دارد.


