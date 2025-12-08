 package Utils;

 import com.codecon.bank_project.Exceptions.BirthDateException;

 import java.time.LocalDate;
 import java.time.Period;
 import java.time.format.DateTimeFormatter;
 import java.time.format.DateTimeFormatterBuilder;
 import java.time.format.DateTimeParseException;
 import java.time.format.ResolverStyle;

 public class Utils {
     public static boolean cpfValidator(String cpf) {
         if (cpf == null) return false;

         // Remove caracteres não numéricos
         cpf = cpf.replaceAll("\\D", "");

         // Verifica se tem 11 dígitos
         if (cpf.length() != 11) return false;

         // Reject CPF numbers with all digits the same (e.g., 00000000000)
         if (cpf.matches("(\\d)\\1{10}")) return false;

         try {
             // Calculates check digits
             int sum = 0;
             for (int i = 0; i < 9; i++) {
                 sum += (cpf.charAt(i) - '0') * (10 - i);
             }
             int remainder = 11 - (sum % 11);
             int digito1 = (remainder == 10 || remainder == 11) ? 0 : remainder;

             sum = 0;
             for (int i = 0; i < 10; i++) {
                 sum += (cpf.charAt(i) - '0') * (11 - i);
             }
             remainder = 11 - (sum % 11);
             int digit2 = (remainder == 10 || remainder == 11) ? 0 : remainder;

             // Verifica se os dígitos calculados conferem
             return digito1 == (cpf.charAt(9) - '0') && digit2 == (cpf.charAt(10) - '0');

         } catch (Exception e) {
             return false;
         }
     }

     public static boolean cepValidator(String cep) {
         if (cep == null) return false;

         // Accept the format 12345-678 or 12345678
         return cep.matches("\\d{5}-?\\d{3}");
     }

     public static LocalDate converteData(String data){
         DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         return LocalDate.parse(data, dateTimeFormatter);
     }

     public static void dateBrithValidate(LocalDate birthDate) {
         int minIdade = 18;
         int maxIdade = 120;

         LocalDate dataAtual = LocalDate.now();

         if (birthDate.isAfter(dataAtual)) {
             throw  new BirthDateException("Erro de Lógica: A data de nascimento não pode ser no futuro.");
         }

         int idade = Period.between(birthDate, dataAtual).getYears();

         if(idade < minIdade){
             throw  new BirthDateException("Erro: Necessário ser maior de 18 anos ! ");
         }

         if (idade > maxIdade) {
             throw  new BirthDateException("Erro de Lógica: Idade excessiva. Pessoa teria mais de " + maxIdade + " anos.");
         }
     }
 }
