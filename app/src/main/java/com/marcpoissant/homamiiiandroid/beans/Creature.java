package com.marcpoissant.homamiiiandroid.beans;

/**
 * Created by marc on 12/06/16.
 */
public class Creature {
    private int id;
    private String name;
    private String army;
    private int health;
    private int speed;
    private int attack;
    private int defense;
    private int minDamage;
    private int maxDamage;
    private String special;
    private int goldCost;
    private int resourceCost;
    private String resourceType;
    private int numShots;
    private boolean canFly;
    private int tierLevel;
    private boolean isUpgradedForm;
    private int upgradeId;

    public Creature() {
        this(-1,"","",0,0,0,0,0,0,"",0,0,"",0,false,0,false,-1);
    }

    public Creature(int id, String name, String army, int health, int speed, int attack,
                    int defense, int minDamage, int maxDamage, String special, int goldCost,
                    int resourceCost, String resourceType, int numShots, boolean canFly, int tierLevel,
                    boolean isUpgradedForm, int upgradeId) {
        super();

        this.id = id;
        this.name = name;
        this.army = army;
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.special = special;
        this.goldCost = goldCost;
        this.resourceCost = resourceCost;
        this.resourceType = resourceType;
        this.numShots = numShots;
        this.canFly = canFly;
        this.tierLevel = tierLevel;
        this.isUpgradedForm = isUpgradedForm;
        this.upgradeId = upgradeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArmy() {
        return army;
    }

    public void setArmy(String army) {
        this.army = army;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        this.goldCost = goldCost;
    }

    public int getResourceCost() {
        return resourceCost;
    }

    public void setResourceCost(int resourceCost) {
        this.resourceCost = resourceCost;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getNumShots() {
        return numShots;
    }

    public void setNumShots(int numShots) {
        this.numShots = numShots;
    }

    public boolean canFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public int getTierLevel() {
        return tierLevel;
    }

    public void setTierLevel(int tierLevel) {
        this.tierLevel = tierLevel;
    }

    public boolean isUpgradedForm() {
        return isUpgradedForm;
    }

    public void setIsUpgradedForm(boolean isUpgradedForm) {
        this.isUpgradedForm = isUpgradedForm;
    }

    public int getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(int upgradeId) {
        this.upgradeId = upgradeId;
    }

    @Override
    public String toString() {
        return "Creature [id=" + id + ", name=" + name + ", army=" + army + ", health=" + health +
                ", speed=" + speed + ", attack=" + attack + ", defense=" + defense +
                ", minDamage=" + minDamage + ", maxDamage=" + maxDamage + ", special=" + special +
                ", goldCost=" + goldCost + ", resourceCost=" + resourceCost + ", resourceType=" +
                resourceType + ", numShots=" + numShots + ", canFly=" + canFly + ", tierLevel=" +
                tierLevel + ", isUpgradedForm=" + isUpgradedForm + ", upgradeId=" + upgradeId + "]";
    }
}
