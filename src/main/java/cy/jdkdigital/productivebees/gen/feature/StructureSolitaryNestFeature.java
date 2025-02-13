package cy.jdkdigital.productivebees.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;

import javax.annotation.Nonnull;
import java.util.Random;

public class StructureSolitaryNestFeature extends SolitaryNestFeature
{
    private final float probability;
    private final int offsetSpan;

    public StructureSolitaryNestFeature(float probability, Codec<ReplaceBlockConfig> configFactory, int offsetSpan) {
        super(probability, configFactory);
        this.offsetSpan = offsetSpan;
        this.probability = probability;
    }

    @Override
    public boolean place(@Nonnull ISeedReader world, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random rand, @Nonnull BlockPos blockPos, @Nonnull ReplaceBlockConfig featureConfig) {
        if (nestShouldNotGenerate(featureConfig) || rand.nextFloat() > this.probability) {
            return false;
        }

        // Get random block in chunk
        blockPos = blockPos.south(rand.nextInt(14)).east(rand.nextInt(14)).above(50);

        BlockStateMatcher matcher = BlockStateMatcher.forBlock(featureConfig.target.getBlock());

        // Go to nearby structure
        nearby:
        if (!matcher.test(world.getBlockState(blockPos))) {
            // Skip or look around?
            for (Direction dir : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
                if (matcher.test(world.getBlockState(blockPos.relative(dir, 2)))) {
                    blockPos = blockPos.relative(dir, 3);
                    break nearby;
                }
            }
            return false;
        }

        // Expand up
        blockPos = blockPos.relative(Direction.UP, world.getRandom().nextInt(this.offsetSpan));

        // Move to structure edge
        edgeFinding:
        for (Direction dir : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
            int i = 0;
            while (++i <= 5) {
                if (world.isEmptyBlock(blockPos.relative(dir, i))) {
                    blockPos = blockPos.relative(dir, i - 1);
                    break edgeFinding;
                }
            }
        }

        return placeNest(world, blockPos, featureConfig);
    }
}
