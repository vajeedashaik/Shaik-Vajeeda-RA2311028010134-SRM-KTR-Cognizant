class Computer {
    private final String cpu;
    private final String ram;
    private final String storage;
    private final String gpu;
    private final boolean bluetoothEnabled;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.bluetoothEnabled = builder.bluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer{cpu='" + cpu + "', ram='" + ram + "', storage='" + storage
             + "', gpu='" + gpu + "', bluetooth=" + bluetoothEnabled + "}";
    }

    public static class Builder {
        // Required
        private final String cpu;
        private final String ram;
        // Optional
        private String storage = "256GB SSD";
        private String gpu = "Integrated";
        private boolean bluetoothEnabled = false;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder bluetoothEnabled(boolean enabled) {
            this.bluetoothEnabled = enabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class Exercise3_Builder {
    public static void main(String[] args) {
        Computer basic = new Computer.Builder("Intel i5", "8GB")
                .build();

        Computer gaming = new Computer.Builder("Intel i9", "32GB")
                .storage("2TB NVMe SSD")
                .gpu("NVIDIA RTX 4080")
                .bluetoothEnabled(true)
                .build();

        Computer workstation = new Computer.Builder("AMD Ryzen 9", "64GB")
                .storage("4TB SSD")
                .gpu("AMD Radeon Pro")
                .bluetoothEnabled(true)
                .build();

        System.out.println("Basic      : " + basic);
        System.out.println("Gaming     : " + gaming);
        System.out.println("Workstation: " + workstation);
    }
}
