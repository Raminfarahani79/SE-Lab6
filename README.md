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


<div dir="rtl">

# سوالات README


## سوال ۱
> الگوهای سازنده (Creational Patterns): این الگوها با فرایند ایجاد اشیا سروکار دارند و راهکارهایی برای جدا کردن فرآیند ساخت اشیا از کلاس‌های استفاده‌کننده ارائه می‌دهند تا وابستگی به پیاده‌سازی‌های خاص کاهش یابد.

> الگوهای ساختاری (Structural Patterns): این الگوها به نحوه ترکیب و تشکیل اشیا و کلاس‌ها برای ایجاد ساختارهای بزرگتر و پیچیده‌تر می‌پردازند، با هدف بهبود انعطاف‌پذیری و کارآیی در ساختار سیستم.

> الگوهای رفتاری (Behavioral Patterns): این الگوها بر تعاملات و مسئولیت‌های بین اشیا و کلاس‌ها تمرکز دارند و راهکارهایی برای مدیریت پیچیدگی‌های ارتباطی و نحوه رفتار سیستم ارائه می‌دهند.


## سوال ۲

> الگو‌های فاز اول از دسته سوم هستند زیرا با مسئولیت بین اشیا سر و کار داریم.


## سوال ۳ 

> برای این کار از الگوی state استفاده می‌کنیم. این الگو مناسب زمانی است که می‌خواهیم یک آبجکت رفتارش را با توجه به حالتی که در آن قرار دارد تغییر دهد. پس با استفاده از دو state برای حالت‌های یک‌طرفه و دو طرفه، امکان رفتن از یک راس به راس دیگر در گراف مشخص می‌شود و می‌توانیم مسیریابی را انجام دهیم.

## سوال ۴

> Single Responsibility Principle (SRP): الگوی Singleton ممکن است این اصل را نقض کند، زیرا علاوه بر وظیفه اصلی کلاس (که می‌تواند ایجاد یک شیء خاص باشد)، مدیریت چرخه عمر آن شیء (مانند ایجاد و دسترسی به تنها نمونه) را نیز بر عهده دارد.

> Open/Closed Principle (OCP): الگوی Singleton معمولاً این اصل را نقض می‌کند، زیرا برای تغییر رفتار یا گسترش عملکرد Singleton، نیاز به تغییر در کد اصلی کلاس است که خلاف باز بودن برای توسعه و بسته بودن برای تغییر است.

> Liskov Substitution Principle (LSP): این اصل معمولاً توسط Singleton نقض نمی‌شود، مگر اینکه پیاده‌سازی‌های خاص Singleton مانع از جایگزینی شیء Singleton با نوع دیگری از آن کلاس شوند.

> Interface Segregation Principle (ISP): الگوی Singleton ذاتاً با این اصل تداخلی ندارد، زیرا معمولاً شامل چندین رابط نیست؛ اما اگر Singleton دارای رابط‌های متعدد با وظایف مختلف باشد، ممکن است نیاز به جداسازی رابط‌ها باشد.

> Dependency Inversion Principle (DIP): الگوی Singleton این اصل را نقض می‌کند، زیرا وابستگی‌های مستقیم به کلاس Singleton ایجاد می‌کند و وابستگی به جزئیات (پیاده‌سازی خاص Singleton) را جایگزین وابستگی به انتزاع (رابط یا کلاس‌های پایه) می‌کند.


## سوال ۵

> کد تمیز: کدی است که خوانا، ساده، سازمان‌یافته و بدون ابهام است، به طوری که به راحتی قابل درک و نگهداری باشد.

> بدهی فنی: مشکلات و نقص‌های فنی ناشی از تصمیمات توسعه سریع یا نادرست که در آینده نیاز به اصلاح یا بازنگری دارند، شبیه به بدهی مالی که باید بازپرداخت شود.

> بوی بد: نشانه‌های قابل تشخیص در کد که به مشکلات احتمالی در طراحی، سازماندهی یا عملکرد کد اشاره دارند و ممکن است نیاز به بهبود داشته باشند.

## سوال ۶

> Bloaters: این دسته شامل بوی بدهایی است که ناشی از رشد بیش از حد اجزای کد (مانند کلاس‌ها، متدها یا پارامترها) هستند و منجر به پیچیدگی و دشواری در نگهداری می‌شوند، مانند متدهای طولانی یا کلاس‌های بزرگ.

> ویژگی‌های شیء (Object-Orientation Abusers): این دسته مربوط به سوءاستفاده یا عدم استفاده مناسب از اصول شیءگرایی است، مانند استفاده نادرست از ارث‌بری یا پیاده‌سازی نادرست انتزاع‌ها که باعث کاهش انعطاف‌پذیری و قابلیت استفاده مجدد می‌شود.

> پیچیدگی (Change Preventers): این بوهای بد کد به تغییرات آینده آسیب می‌زنند و شامل کدهایی هستند که تغییر یا گسترش آنها سخت و خطرناک است، مانند وابستگی‌های چندگانه یا داده‌های منتشر شده در کلاس‌های مختلف.

> Dispensables: این دسته شامل اجزایی است که وجود آنها ضروری نیست و می‌توان آنها را حذف کرد، مانند کدهای مرده، تکراری یا نظرات غیرضروری که تنها باعث گیج شدن خواننده می‌شوند.

> Couplers: این بوهای بد ناشی از وابستگی‌های بیش از حد بین کلاس‌ها و ماژول‌ها هستند که منجر به کدهای سخت نگهدار و ضعیف در برابر تغییرات می‌شوند، مانند کلاس‌های وابسته به پیاده‌سازی یکدیگر به جای استفاده از انتزاع.


## سوال ۷

> Lazy Class جزو دسته Dispensables است.

> Inline Class (درون‌خطی‌کردن کلاس): اگر کلاس Lazy بسیار کوچک و وظایفش را به درستی انجام نمی‌دهد، می‌توان کد آن را به کلاس والد یا کلاس دیگری که از آن استفاده می‌کند منتقل کرد و خود کلاس را حذف نمود.

> Collapse Hierarchy (فروپاشی سلسله‌مراتب): اگر کلاس Lazy تنها یک زیرکلاس یا یک والد دارد و کار اضافی یا مهمی انجام نمی‌دهد، می‌توان سلسله‌مراتب را فروپاشید و تمام وظایف را به کلاس دیگر منتقل کرد.

## سوال ۸

> کلاس DependencyEdge از نوع `Data Class` است.

> تابع getEdgeType در کلاس بالا هیچ کاربردی ندارد و `Dead Code` است.

> حجم زیادی `Comments` در کل پروژه قرار دارد.

> انواع `Long Method` با طول (بیش از ۶۰ خط) در کد وجود دارد.

> تعداد زیادی `Switch Statements` روی فیلد currentNode.getNodeName() وجود دارد.

> در مواقع زیادی تعدادی فیلد مثل  completeClass, className باهم تکرار شده و `Data Clumps` می‌سازند.

> طول برخی کلاس‌ها مثل  Phase1CodeGenerator زیاد است و `Large Class` ساخته است.

> توابع زیادی در کلاس Phase1CodeGenerator مثل generateOneUnionUsage وجود دارند که بهتر است به کلاس CompleteClass منتقل شده تا از `Feature Envy` جلوگیری شود.

> دو تابع ClassConstructor و ClassMethod باهم تعدادی `Duplicate Code` دارند.

> فیلد سراسری documentFactory در Main تنها در صورت خاصی استفاده خواهد شد که از آن یک `Primitive Obsession` خواهد ساخت.


## سوال ۹

> پلاگین Formatter ابزاری است که برای خودکارسازی فرآیند فرمت کردن کدها به کار می‌رود. این پلاگین با اعمال استانداردهای مشخصی بر روی ساختار کد، اطمینان حاصل می‌کند که کدهای نوشته شده در یک پروژه به شکل یکسان و قابل خواندن فرمت شده‌اند. از جمله وظایف این پلاگین‌ها می‌توان به مرتب‌سازی فاصله‌ها (indentation)، حذف فضاهای اضافی، یکسان‌سازی استفاده از نقل‌قول‌ها، و قالب‌بندی خطوط کد اشاره کرد.

> بازآرایی کد فرآیندی است که طی آن کد بهبود یافته و ساختاردهی مجدد می‌شود تا قابل نگهداری و توسعه باشد، بدون تغییر در عملکرد اصلی کد. یکی از اولین قدم‌ها در بازآرایی، اطمینان از درست فرمت بودن کد است. Formatter به عنوان یک ابزار پایه‌ای می‌تواند به آماده‌سازی کد برای بازآرایی کمک کند، زیرا با حذف پیچیدگی‌های ظاهری و ایجاد ساختار یکسان، تحلیل و بهبود کد آسان‌تر می‌شود. در نتیجه، استفاده از Formatter قبل از بازآرایی می‌تواند فرآیند بازآرایی را ساده‌تر و موثرتر کند.

</div>
