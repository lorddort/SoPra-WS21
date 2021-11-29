<template>
    <div>
        <b-row>
            <b-col >
                <b-container>
                    <b-dropdown block size="md" id="graph-settings" text="Settings" class="pt-1">
                        <b-dropdown-item>Time Frame</b-dropdown-item>
                        <b-dropdown-item>Currency</b-dropdown-item>
                    </b-dropdown><br>
                    <b-dropdown block id="Courses" text="Courses" class="py-1">
                        <b-dropdown-item>Bitcoin</b-dropdown-item>
                        <b-dropdown-item>Etherium</b-dropdown-item>
                    </b-dropdown><br>
                    <b-button block>Export</b-button>
                </b-container>
            </b-col>
            <b-col cols="10">
                <graph-card></graph-card>
            </b-col>
        </b-row>
    </div>
</template>