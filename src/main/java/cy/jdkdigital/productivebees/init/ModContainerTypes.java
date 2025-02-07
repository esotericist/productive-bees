package cy.jdkdigital.productivebees.init;

import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.container.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes
{
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, ProductiveBees.MODID);

    public static final RegistryObject<ContainerType<AdvancedBeehiveContainer>> ADVANCED_BEEHIVE = CONTAINER_TYPES.register("advanced_beehive", () ->
            IForgeContainerType.create(AdvancedBeehiveContainer::new)
    );
    public static final RegistryObject<ContainerType<CentrifugeContainer>> CENTRIFUGE = CONTAINER_TYPES.register("centrifuge", () ->
            IForgeContainerType.create(CentrifugeContainer::new)
    );
    public static final RegistryObject<ContainerType<PoweredCentrifugeContainer>> POWERED_CENTRIFUGE = CONTAINER_TYPES.register("powered_centrifuge", () ->
            IForgeContainerType.create(PoweredCentrifugeContainer::new)
    );
    public static final RegistryObject<ContainerType<BottlerContainer>> BOTTLER = CONTAINER_TYPES.register("bottler", () ->
            IForgeContainerType.create(BottlerContainer::new)
    );
    public static final RegistryObject<ContainerType<FeederContainer>> FEEDER = CONTAINER_TYPES.register("feeder", () ->
            IForgeContainerType.create(FeederContainer::new)
    );
    public static final RegistryObject<ContainerType<IncubatorContainer>> INCUBATOR = CONTAINER_TYPES.register("incubator", () ->
            IForgeContainerType.create(IncubatorContainer::new)
    );
    public static final RegistryObject<ContainerType<CatcherContainer>> CATCHER = CONTAINER_TYPES.register("catcher", () ->
            IForgeContainerType.create(CatcherContainer::new)
    );
    public static final RegistryObject<ContainerType<HoneyGeneratorContainer>> HONEY_GENERATOR = CONTAINER_TYPES.register("honey_generator", () ->
            IForgeContainerType.create(HoneyGeneratorContainer::new)
    );
    public static final RegistryObject<ContainerType<GeneIndexerContainer>> GENE_INDEXER = CONTAINER_TYPES.register("gene_indexer", () ->
            IForgeContainerType.create(GeneIndexerContainer::new)
    );
}
