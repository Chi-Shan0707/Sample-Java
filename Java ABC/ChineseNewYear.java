public class ChineseNewYear {

    // 1. 定义作为泛型参数的 Horse (马) 类
    static class Horse {
        @Override
        public String toString() {
            return "Horse (马)";
        }
    }

    // 2. 定义泛型类 Year<T>
    static class Year<T> {
        private boolean isHappy;

        // 设置是否快乐的方法
        public void setHappy(boolean isHappy) {
            this.isHappy = isHappy;
            if (isHappy) {
                celebrate();
            }
        }

        // 庆祝方法
        private void celebrate() {
            System.out.println("-----------------------------");
            System.out.println("Happy Chinese New Year! (CNY)");
            System.out.println("祝大家马年大吉，代码无Bug！");
            System.out.println("-----------------------------");
        }
    }

    // 3. 主程序入口
    public static void main(String[] args) {
        // --- 图中的原始代码 ---
        
        // 使用了 Raw Type (原生类型) 的写法
        // 虽然右边指定了 <Horse>，但左边 cny 丢失了泛型信息
        Year cny = new Year<Horse>(); 
        
        // 调用方法，触发庆祝逻辑
        cny.setHappy(true);
        
        // --------------------
    }
}