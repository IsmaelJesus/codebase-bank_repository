// package utils;

// import com.codecon.bank_project.Enum.ClientTypeEnum;

// import java.time.LocalDate;
// import java.time.Period;
// import java.time.format.DateTimeFormatter;
// import java.time.format.DateTimeParseException;
// import java.time.format.ResolverStyle;

// public class Utils {
//     public static boolean validarCPF(String cpf) {
//         if (cpf == null) return false;

//         // Remove caracteres não numéricos
//         cpf = cpf.replaceAll("\\D", "");

//         // Verifica se tem 11 dígitos
//         if (cpf.length() != 11) return false;

//         // Rejeita CPFs com todos os dígitos iguais (ex: 00000000000)
//         if (cpf.matches("(\\d)\\1{10}")) return false;

//         try {
//             // Calcula os dígitos verificadores
//             int soma = 0;
//             for (int i = 0; i < 9; i++) {
//                 soma += (cpf.charAt(i) - '0') * (10 - i);
//             }
//             int resto = 11 - (soma % 11);
//             int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

//             soma = 0;
//             for (int i = 0; i < 10; i++) {
//                 soma += (cpf.charAt(i) - '0') * (11 - i);
//             }
//             resto = 11 - (soma % 11);
//             int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

//             // Verifica se os dígitos calculados conferem
//             return digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0');

//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public static boolean validarCEP(String cep) {
//         if (cep == null) return false;

//         // Aceita formato 12345-678 ou 12345678
//         return cep.matches("\\d{5}-?\\d{3}");
//     }

//     public static LocalDate converteData(String data){
//         DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//         return LocalDate.parse(data, dateTimeFormatter);
//     }

//     public static boolean validarDataNascimento(String dataStr) {
//         dataStr = dataStr.trim();
//         int minIdade = 18;
//         int maxIdade = 120;
//         String formato = "dd/MM/uuuu";

//         DateTimeFormatter formatter = DateTimeFormatter
//                 .ofPattern(formato)
//                 .withResolverStyle(ResolverStyle.STRICT);

//         LocalDate dataNascimento;

//         try {
//             dataNascimento = LocalDate.parse(dataStr, formatter);
//         } catch (DateTimeParseException e) {
//             System.out.println("Erro de Formato/Data: A string '" + dataStr +
//                     "' não está no formato '" + formato + "' ou é uma data inexistente.");
//             return false;
//         }

//         LocalDate dataAtual = LocalDate.now();

//         if (dataNascimento.isAfter(dataAtual)) {
//             System.out.println("Erro de Lógica: A data de nascimento não pode ser no futuro.");
//             return false;
//         }

//         int idade = Period.between(dataNascimento, dataAtual).getYears();

//         if(idade < minIdade){
//             System.out.println("Erro: Necessário ser maior de 18 anos ! ");
//             return false;
//         }

//         if (idade > maxIdade) {
//             System.out.println("Erro de Lógica: Idade excessiva. Pessoa teria mais de " + maxIdade + " anos.");
//             return false;
//         }

//         return true;
//     }

//     public static ClientTypeEnum  getTipoCliente(int opc) {
//         switch (opc) {
//             case 1 -> {
//                 return ClientTypeEnum.COMMON;
//             }
//             case 2 -> {
//                 return ClientTypeEnum.SUPER;
//             }
//             case 3 -> {
//                 return ClientTypeEnum.PREMIUM;
//             }
//             default -> {
//                 return null;
//             }
//         }
//     }
// }
