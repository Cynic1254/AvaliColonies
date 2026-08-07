package com.cynic1254.avalitheme.mixin;

import com.cynic1254.avalitheme.GeoAbstractEntityCitizen;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractEntityCitizen.class)
public class AbstractEntityCitizenMixin implements GeoAbstractEntityCitizen {
}
