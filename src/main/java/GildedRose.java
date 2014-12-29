        import java.util.ArrayList;
        import java.util.List;


        public class GildedRose {

            public static final int MINIMUM_QUALITY = 0;
            public static final int MAX_QUALITY = 50;
            public static final int MINIMUN_SELLIN = 0;
            public static final int RESET_QUALITY = 0;
            public static final int SELLIN = 1;
            public static final int QUALITY = 1;
            public static final int FIRST_THRESHOLD_SELLIN = 10;
            public static final int SECOND_THRESHOLD_SELLIN = 5;
            private static List<Item> items = null;

            /**
             * @param args
             */
            public static void main(String[] args) {

                System.out.println("OMGHAI!");

                items = new ArrayList<Item>();
                items.add(new Item("+5 Dexterity Vest", 10, 20));
                items.add(new Item("Aged Brie", 2, 0));
                items.add(new Item("Elixir of the Mongoose", 5, 7));
                items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
                items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
                items.add(new Item("Conjured Mana Cake", 3, 6));

                updateQuality();
        }



            public static void updateQuality(){
                for (int i = 0; i < items.size(); i++){

                    Item current = items.get(i);
                    int itemQuality = current.getQuality();
                    boolean hasSomeQuality = hasSomeQuality(itemQuality);
                    String currentItemName = current.getName();
                    boolean notBrie = !"Aged Brie".equals(currentItemName);
                    boolean isItPass = isItAPass(currentItemName);
                    boolean notPasses = !isItPass;
                    boolean norBrieEitherPasses = notBrie && notPasses;
                    boolean notSulfuras = notSulfuras(currentItemName);
                    boolean qualityNotReached = quealityNotReached(itemQuality);


                    decreaseQualityIfItIsNotSulturas(current);

                    if (norBrieEitherPasses){
                        decreaseQualityIfItIsNotSulturas(current);
                    }else {
                            increaseQuality(current);
                                if (isItInFirstThresholdAndIsItPass(current)){
                                        increaseQuality(current);
                                }

                                if (isItInSecondThresholdAndIsItPass(current)){
                                        increaseQuality(current);
                                }
                    }

                    if (hasSomeSellIn(current)){
                        if (notBrie){
                            if (notPasses && hasSomeQuality){
                                    decreaseQualityIfItIsNotSulturas(current);
                            }
                            else{
                                current.setQuality(RESET_QUALITY);
                            }
                        }
                        else{
                                increaseQuality(current);
                        }
                    }
                }
            }

            private static boolean isItAPass(String currentItemName) {
                return "Backstage passes to a TAFKAL80ETC concert".equals(currentItemName);
            }

            private static boolean isItInSecondThreshold(Item current) {
                return current.getSellIn() <= SECOND_THRESHOLD_SELLIN;
            }


            private static boolean isItInSecondThresholdAndIsItPass(Item current) {
                return isItAPass(current.getName())  && isItInSecondThreshold(current);
            }

            private static boolean isItInFirstThreshold(Item current) {
                return current.getSellIn() <= FIRST_THRESHOLD_SELLIN;
            }

            private static boolean isItInFirstThresholdAndIsItPass(Item current) {
                    return isItAPass(current.getName())  && isItInFirstThreshold(current);
            }

            private static boolean hasSomeSellIn(Item current) {
                return current.getSellIn() < MINIMUN_SELLIN;
            }

            private static boolean notSulfuras(String currentItemName) {
                return !"Sulfuras, Hand of Ragnaros".equals(currentItemName);
            }

            private static boolean hasSomeQuality(int itemQuality) {
                return itemQuality > MINIMUM_QUALITY;
            }

            private static boolean quealityNotReached(int itemQuality) {
                return itemQuality < MAX_QUALITY;
            }


            private static void decreaseSellIn(Item current) {
                current.setSellIn(current.getSellIn() - SELLIN);
            }

            private static void decreaseQuality(Item current){
                if (hasSomeQuality(current.getQuality())){
                    current.setQuality(current.getQuality() - QUALITY);
                }
            }

            private static void decreaseQualityIfItIsNotSulturas(Item current){

                if (hasSomeQuality(current.getQuality()) && (notSulfuras(current.getName()))){
                    current.setQuality(current.getQuality() - QUALITY);
                }
            }

            private static void increaseQuality(Item current) {
                if (quealityNotReached(current.getQuality())){
                    current.setQuality(current.getQuality() + QUALITY);
                }
            }

        }