package cy.jdkdigital.productivebees.common.advancements.criterion;

import com.google.gson.JsonObject;
import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.common.entity.bee.ConfigurableBeeEntity;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class FishBeeTrigger extends AbstractCriterionTrigger<FishBeeTrigger.Instance>
{
    private static final ResourceLocation ID = new ResourceLocation(ProductiveBees.MODID, "fish_bee");

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity player, BeeEntity bee) {
        this.trigger(player, trigger -> trigger.test(bee));
    }

    @Nonnull
    @Override
    protected Instance createInstance(JsonObject jsonObject, EntityPredicate.AndPredicate andPredicate, ConditionArrayParser conditionArrayParser) {
        return new FishBeeTrigger.Instance(JSONUtils.getAsString(jsonObject, "beeName"));
    }

    public static class Instance extends CriterionInstance
    {
        private final String beeName;

        public Instance(String beeName) {
            super(FishBeeTrigger.ID, EntityPredicate.AndPredicate.ANY);
            this.beeName = beeName;
        }

        public static FishBeeTrigger.Instance any() {
            return new FishBeeTrigger.Instance("any");
        }

        public static FishBeeTrigger.Instance create(String beeName) {
            return new FishBeeTrigger.Instance(beeName);
        }

        public boolean test(BeeEntity bee) {
            String type = bee instanceof ConfigurableBeeEntity ? ((ConfigurableBeeEntity) bee).getBeeType() : bee.getEncodeId();

            return this.beeName.equals("any") || (type != null && type.equals(this.beeName));
        }

        @Nonnull
        @Override
        public JsonObject serializeToJson(ConditionArraySerializer serializer) {
            JsonObject jsonobject = super.serializeToJson(serializer);
            jsonobject.addProperty("beeName", this.beeName);
            return jsonobject;
        }
    }
}
