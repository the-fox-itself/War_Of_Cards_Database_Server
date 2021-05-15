package GameMechanic.Objects.Grounds;

public class GroundEmpty extends Ground {
    public GroundEmpty(int xOnWorld, int yOnWorld) {
        super(xOnWorld, yOnWorld);
        this.icon = null;
        this.name = NAME_EMPTY;
        System.out.println("New ground has created: " + name + ".");
    }

    @Override
    public void recovery() {
        icon = null;
    }
}
