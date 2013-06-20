package organisms.g2;

import java.awt.Color;
import java.util.Random;

import organisms.Move;
import organisms.OrganismsGame;
import organisms.Player;
import organisms.g2.data.MoveInput;
import organisms.g2.stats.Stats;

public abstract class PlayerBase implements Player {
	private static final long serialVersionUID = -2855747689179359665L;

	private Random rand;
	private int state;
	private OrganismsGame game;
	private Color color;

	private Integer offspringCount;
	private Integer age;
	private Integer ageAtWhichWeHadLastChild;
	private String name;
	private MoveFactory moveFactory;
	
	@Override
	public void register(OrganismsGame __amoeba, int key) throws Exception {
		setState(0);
		setGame(__amoeba);
		setOffspringCount(0);
		setAge(0);
		setAgeAtWhichWeHadLastChild(0);
		setColor(Color.GREEN);
		setMoveFactory(new MoveFactory());
		setRand(new Random());
	}

	@Override
	public String name() throws Exception {
		return getName();
	}

	@Override
	public Color color() throws Exception {
		return getColor();
	}

	@Override
	public boolean interactive() throws Exception {
		return false;
	}

	@Override
	public Move move(boolean[] foodpresent, int[] neighbors, int foodleft, int energyleft)
			throws Exception {
		setAge(getAge() + 1);
		return move(MoveInput.createMoveInput(foodpresent, neighbors, foodleft, energyleft));
	}
	
	public abstract Move move(MoveInput input);

	@Override
	public int externalState() throws Exception {
		return getState();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Integer getOffspringCount() {
		return offspringCount;
	}

	public void setOffspringCount(Integer offspringCount) {
		this.offspringCount = offspringCount;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAgeAtWhichWeHadLastChild() {
		return ageAtWhichWeHadLastChild;
	}

	public void setAgeAtWhichWeHadLastChild(Integer ageAtWhichWeHadLastChild) {
		this.ageAtWhichWeHadLastChild = ageAtWhichWeHadLastChild;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public OrganismsGame getGame() {
		return game;
	}

	public void setGame(OrganismsGame game) {
		this.game = game;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public MoveFactory getMoveFactory() {
		return moveFactory;
	}

	public void setMoveFactory(MoveFactory moveFactory) {
		this.moveFactory = moveFactory;
	}

	protected Random getRand() {
		return rand;
	}

	protected void setRand(Random rand) {
		this.rand = rand;
	}

	protected Move randomReproduce() {
		return createReproductionMove(PlayerUtil.getRandomCardinalDirection(getRand()));
	}
	
	protected Move createStayPutMove() {
		return new Move(STAYPUT);
	}
	
	protected Move createMove(int direction) {
		return new Move(direction);
	}
	
	protected Move createReproductionMove(int direction) {
		return createReproductionMove(direction, getState());
	}

	protected Move createReproductionMove(int direction, Integer state) {
		setOffspringCount(getOffspringCount() + 1);
		setAgeAtWhichWeHadLastChild(getAge());
		return new Move(REPRODUCE, direction, state);
	}
	
	protected Boolean nOutOfMTimes(int n, int m) {
		return PlayerUtil.nOutOfMTimes(n, m, rand);
	}
	
	protected int getStepsSinceWeHadLastChild() {
		return getAge() - getAgeAtWhichWeHadLastChild();
	}
	
	
	protected Double factorOfMaximumEnergyPerOrganism(Double factor) {
		return getMaximumEnergyPerOrganismM() * factor;
	}

	protected boolean weHaveFewNeighbors(MoveInput input) {
		return Stats.neighborCount(input.getNeighbors()) < 1;
	}

	protected Boolean nStepsHavePassedSinceWeLastHadChild(int steps) {
		return getStepsSinceWeHadLastChild() > steps;
	}
	
	protected Integer getMaximumEnergyPerOrganismM() {
		return getGame().M();
	}
	
	protected Integer getEnergyConsumedByMovingOrReproducingV() {
		return getGame().v();
	}
	
	protected Integer getEnergyPerUnitOfFoodU() {
		return getGame().u();
	}
	
	protected Integer getMaximumFoodUnitsPerCellK(){
		return getGame().K();
	}
}
