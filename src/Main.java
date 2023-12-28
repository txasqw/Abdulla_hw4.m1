import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 270, 280, 250,350,225,260,250};
    public static int[] heroesDamage = {25, 20, 15, 0, 5,15,25,20};


    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic","Golem", "Lucky","Berserk", "Thor"};
    public static int roundNumber = 0;

    public static void main(String[] args) {

        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicHeal();
        golemDefence();
        luckyEvasion();
        berserkHit();
        thorStan();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }

    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2; // 2,3,4,5,6,7
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }

    public static void medicHeal(){
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (i ==3){
                continue;
            }
            if (heroesHealth[i] >0 && heroesHealth[i] < 100){
                int heal1=30;
                int heal2=80;
                int heal = heal1 + (int) (Math.random() * heal2);
                heroesHealth[i] = heal + heroesHealth[i];
            }
            System.out.println("Medic Healed : " + heroesAttackType[i]);
            break;
        }
    }

    public static void golemDefence(){
        int damageOfBoss =bossDamage/5;
        int healthOfHeroes = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i==5){
                continue;
            } else if (heroesHealth[i]>0) {
                healthOfHeroes++;
                heroesHealth[i] += damageOfBoss;
            }
        }
    }

    public static void luckyEvasion(){
        Random random = new Random();
        boolean evasion = random.nextBoolean();
        if (heroesHealth[5] >0 && evasion) {
            heroesHealth[5] += bossDamage - bossDamage/5;
            System.out.println(" Уклонился?: " + evasion);
        }
    }
    public static void berserkHit(){
        Random random = new Random();
        int randomHit = random.nextInt(15)+1;
        int randomDamage = random.nextInt(3)+1;
        if (heroesHealth[4]> 0 && bossHealth > 0){
            switch (randomDamage){
                case 1:
                    heroesDamage[4] = (heroesDamage[4] + bossDamage) - randomHit;
                    System.out.println(" Берсерк нанесет критический урон " );
                    System.out.println(" Урон Берсерка увеличен на " + randomHit);
                    break;
                case 2:
                    bossDamage = 50;
                    break;
                case 3:
                    bossDamage = 50;
                    break;
            }
        }
    }

    public static void thorStan(){
        Random random = new Random();
        boolean thorStan = random.nextBoolean();
        if (thorStan){
            bossDamage = 0;
            System.out.println("Boss оглушен на 1 раунд");
        }
        else {
            bossDamage = 50;
        }
    }
}