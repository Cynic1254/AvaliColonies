package com.cynic1254.avalitheme.client.rendering;

import com.cynic1254.avalitheme.Avalitheme;
import com.cynic1254.avalitheme.client.rendering.Layers.CitizenItemGeoLayer;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.apiimp.initializer.ModModelTypeInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

import java.util.Objects;

public class GeoCitizenRenderer extends GeoReplacedEntityRenderer<AbstractEntityCitizen, GeoCitizenAnimatable> {
    private static final ResourceLocation PLACEHOLDER_MODEL =
            ResourceLocation.fromNamespaceAndPath(Avalitheme.MODID, "genericcitizen");

    public GeoCitizenRenderer(EntityRendererProvider.Context context, GeoCitizenAnimatable animatable) {
        super(context, new DefaultedEntityGeoModel<>(PLACEHOLDER_MODEL, "Head"), animatable);
        ModModelTypeInitializer.init(context);

        addRenderLayer(new CitizenItemGeoLayer(this));
    }

    @Override
    public GeoModel<GeoCitizenAnimatable> getGeoModel() {
        return super.getGeoModel();
    }

    @Override
    public ResourceLocation getTextureLocation(GeoCitizenAnimatable animatable) {
        // 1. Fetch the active MineColonies citizen entity being rendered
        AbstractEntityCitizen entity = getCurrentEntity();

        if (entity != null) {
            // 2. Try fetching custom texture from CitizenDataView
            if (entity.getCitizenDataView() != null && entity.getCitizenDataView().getCustomTexture() != null) {
                ResourceLocation customTex = entity.getCitizenDataView().getCustomTexture();
                if (textureExists(customTex)) {
                    return customTex;
                }
            }

            // 3. Try fetching standard MineColonies entity texture
            ResourceLocation defaultTex = entity.getTexture();
            if (textureExists(defaultTex)) {
                return defaultTex;
            }
        }

        // 4. Fallback texture when entity texture doesn't exist on disk
        return ResourceLocation.fromNamespaceAndPath(Avalitheme.MODID, "textures/entity/genericcitizen.png");
    }

    /**
     * Helper to check if a ResourceLocation actually exists in client assets
     */
    private boolean textureExists(ResourceLocation location) {
        return Minecraft.getInstance().getResourceManager().getResource(location).isPresent();
    }
}
