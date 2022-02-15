package cz.cvut.fel.omo.smarthome.models.house.devices.state;

public enum CookState {
    Off {
        @Override
        public CookState nextState() {
            return Preheating;
        }
    },
    Preheating {
        @Override
        public CookState nextState() {
            return Cooking;
        }
    },
    Cooking {
        @Override
        public CookState nextState() {
            return Done;
        }
    },
    Done {
        @Override
        public CookState nextState() {
            return Off;
        }
    };

    public abstract CookState nextState();
}
