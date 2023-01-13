package com.cryptocolleagues.services;

import com.cryptocolleagues.controllers.AuthController;
import com.cryptocolleagues.enums.RoleEnum;
import com.cryptocolleagues.models.Post;
import com.cryptocolleagues.models.User;
import com.cryptocolleagues.repositories.PostRepository;
import com.cryptocolleagues.repositories.RoleRepository;
import com.cryptocolleagues.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Profile("demo")

public class DataGeneratorService {
    final Environment env;

    final RoleService roleServiceService;

    final RoleRepository roleRepository;

    final UserRepository userRepository;

    final AuthController authController;

    final PasswordEncoder encoder;

    final PostRepository postRepository;

    @Bean
    public void loadFakeData(){
        try {
            loadDataRolesAndUsers();
        } catch (Exception e) {
            System.err.printf("Couldn't load fake data: %s\n", e.getMessage());
        }
    }

    private void loadDataRolesAndUsers() throws Exception {

        //loading roles

        var roleAdmin = roleServiceService.create(RoleEnum.ROLE_ADMIN);
        var roleUser = roleServiceService.create(RoleEnum.ROLE_USER);

        //loading users

        var user1 = new User("demi", "demi@test.com", encoder.encode("123456"));
        user1.setRoles(Set.of(roleAdmin, roleUser));
        userRepository.save(user1);

        var user2 = new User("alissia", "alissia@test.com", encoder.encode("123456"));
        user2.setRoles(Set.of(roleAdmin, roleUser));
        userRepository.save(user2);

        var user3 = new User("martina", "martina@test.com", encoder.encode("123456"));
        user3.setRoles(Set.of(roleUser));
        userRepository.save(user3);

        //loading posts

        var post1 = new Post("Ballenas de bitcoin impulsan su precio a USD 19.000", "Aumentan las transacciones de las ballenas de bitcoin a más de USD 1 millón.",
                "El precio de bitcoin (BTC) subió este 13 de enero a los USD 19.000, su máximo de dos meses, según datos de TradingView. Precisamente no cotizaba en este nivel desde el 8 de noviembre, pero el día de hoy logró romper esta resistencia tras un aumento en la demanda, de donde destacan los inversionistas ballenas por su enorme presencia. «Las ballenas están comenzando a interesarse y es probable que perpetúen este ascenso», informó la firma de análisis de blockchain Santiment. Se entiende por «ballenas» a aquellos inversionistas que tienen más de 1.000 BTC, equivalente al momento de esta redacción a USD 19 millones.",
                user1);
        postRepository.save(post1);
        var post2 = new Post("Crypto and Football Don’t Mix, and Now We Have Proof", "UK football teams were happy to take crypto money, and crypto money was happy to invest in football. The results are in.",
                "A wide range of major media groups have taken legal action to reveal the identities of the two non-parental parties who co-signed Sam Bankman-Fried's $250 million bail bond. Following the collapse of crypto exchange FTX, Bankman-Fried was arrested in the Bahamas and then extradited to the U.S. to face a number of charges from federal prosecutors. A U.S. judge in late December released Bankman-Fried on $250 million bail backed by his parents and two other parties whom his lawyers requested remain anonymous, citing privacy and safety concerns. The public’s interest in this matter cannot be overstated,” said one filing made Thursday to Judge Lewis Kaplan in the Southern District of New York on behalf of the Associated Press, Bloomberg, Financial Times, CNBC, Reuters, Dow Jones (publisher of the Wall Street Journal) and Washington Post, among others. Mr. Bankman-Fried stands accused of perpetrating one of the largest financial frauds in history.",
                user2);
        postRepository.save(post2);
        var post3 = new Post("MANA Targets $0.5 After a Week of Bullish Activity ", "Decentraland price action: Coinmarketcap",
        "The altcoin has been trading in a range between $0.35 and $0.45 for the past few weeks, and with the recent bullish spike, we expect it to break the resistance level and continue its uptrend toward the next resistance at around $0.68. Decentraland has formed a bullish flag pattern which is a continuation pattern illustrating that the previous upward trend will continue. The price of MANA is trading above the 200-day moving average, and the RSI is at 71, which indicates that there is more room for appreciation. The Exponential Moving Average of 50(EMA) is also positive and is above the EMA200, which means that we can expect an increase in buying pressure. Bulls have been getting increasingly active, which could push the price higher.",
        user1);
        postRepository.save(post3);

    }
}
