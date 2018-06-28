package org.nosreme.system.logical;

import java.util.Set;

public interface Component extends ServiceProvider
{
	ComponentDescriptor getDescriptor();
	Set<Component> getDependencies();
}
