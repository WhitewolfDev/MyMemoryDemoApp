package com.whitewolf.mymemorydemoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.whitewolf.mymemorydemoapp.helper.MemoryHelper;

public class MemoryAllocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_allocation);

        TextView tv_block_size = (TextView) findViewById(R.id.memory_alloc_block_size);
        TextView tv_avail_blocks = (TextView) findViewById(R.id.memory_alloc_available_blocks);
        TextView tv_avail_bytes = (TextView) findViewById(R.id.memory_alloc_available_bytes);
        TextView tv_system_blocks = (TextView) findViewById(R.id.memory_alloc_block_system);
        TextView tv_system_bytes = (TextView) findViewById(R.id.memory_alloc_bytes_total);
        TextView tv_free_blocks = (TextView) findViewById(R.id.memory_alloc_block_free);
        TextView tv_free_bytes = (TextView) findViewById(R.id.memory_alloc_bytes_free);

        tv_block_size.setText(MemoryHelper.getAvailableMemoryFormatted(
                MemoryHelper.getBlockSize(
                        MemoryHelper.getPath(
                                MemoryHelper.PATH_STORAGE_INTERNAL)),
                MemoryHelper.MEMORY_UNIT_B, "0"));
        tv_avail_blocks.setText(MemoryHelper.getAvailableMemoryFormatted(
                MemoryHelper.getAvailableBlocks(
                        MemoryHelper.getPath(
                                MemoryHelper.PATH_STORAGE_INTERNAL)),
                MemoryHelper.MEMORY_UNIT_BLOCK, "0"));
        tv_avail_bytes.setText(MemoryHelper.getAvailableMemoryFormatted(
                MemoryHelper.getAvailableBytes(
                        MemoryHelper.getPath(
                                MemoryHelper.PATH_STORAGE_INTERNAL)),
                MemoryHelper.MEMORY_UNIT_MB, "0.00"));
        tv_system_blocks.setText(MemoryHelper.getAvailableMemoryFormatted(
                MemoryHelper.getTotalSystemBlocks(
                        MemoryHelper.getPath(
                                MemoryHelper.PATH_STORAGE_INTERNAL)),
                MemoryHelper.MEMORY_UNIT_BLOCK, "0"));
        tv_system_bytes.setText(MemoryHelper.getAvailableMemoryFormatted(
                MemoryHelper.getTotalSupportedBytes(
                        MemoryHelper.getPath(
                                MemoryHelper.PATH_STORAGE_INTERNAL)),
                MemoryHelper.MEMORY_UNIT_MB, "0.00"));
        tv_free_blocks.setText(MemoryHelper.getAvailableMemoryFormatted(
                MemoryHelper.getFreeBlocksPlus(
                        MemoryHelper.getPath(
                                MemoryHelper.PATH_STORAGE_INTERNAL)),
                MemoryHelper.MEMORY_UNIT_BLOCK, "0"));
        tv_free_bytes.setText(MemoryHelper.getAvailableMemoryFormatted(
                MemoryHelper.getFreeBytesPlus(
                        MemoryHelper.getPath(
                                MemoryHelper.PATH_STORAGE_INTERNAL)),
                MemoryHelper.MEMORY_UNIT_MB, "0.00"));
    }
}
