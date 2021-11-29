<template>
    <div>
        <b-row>
            <b-col >
                <b-container>
                    <b-dropdown block size="md" id="graph-settings" text="Settings" class="py-2">
                        <b-dropdown-item>Time Frame</b-dropdown-item>
                        <b-dropdown-item>Currency</b-dropdown-item>
                    </b-dropdown><br>
                    <b-dropdown block id="Courses" text="Courses" class="py-2">
                        <b-dropdown-item>Bitcoin</b-dropdown-item>
                        <b-dropdown-item>Etherium</b-dropdown-item>
                    </b-dropdown><br>
                    <b-button block class="py-2">Export</b-button>
                </b-container>
            </b-col>
            <b-col cols="10">

            </b-col>
        </b-row>
    </div>
</template>