#!/usr/bin/env python3
"""
Script to count POTD problem folders and update README.md
"""

import os
import re
from datetime import datetime, timedelta
from pathlib import Path

def count_potd_folders():
    """Count folders that match POTD pattern (4 digits followed by hyphen)"""
    current_dir = Path('.')
    potd_pattern = re.compile(r'^\d{4}-')
    
    potd_folders = [
        folder for folder in current_dir.iterdir()
        if folder.is_dir() and potd_pattern.match(folder.name)
    ]
    
    return len(potd_folders)

def calculate_streak_from_commits():
    """
    Calculate current and longest streak based on commit history
    Assumes one commit per day for POTD
    """
    try:
        import subprocess
        
        # Get commit dates for the last 60 days
        result = subprocess.run(
            ['git', 'log', '--since="60 days ago"', '--date=short', '--pretty=format:%cd'],
            capture_output=True,
            text=True,
            check=True
        )
        
        if not result.stdout:
            return 0, 0
        
        # Parse commit dates
        commit_dates = set(result.stdout.strip().split('\n'))
        dates = sorted([datetime.strptime(d, '%Y-%m-%d').date() for d in commit_dates if d], reverse=True)
        
        if not dates:
            return 0, 0
        
        # Calculate current streak
        current_streak = 0
        today = datetime.now().date()
        check_date = today
        
        for date in dates:
            if date == check_date or date == check_date - timedelta(days=1):
                current_streak += 1
                check_date = date - timedelta(days=1)
            else:
                break
        
        # Calculate longest streak
        longest_streak = 0
        temp_streak = 1
        
        for i in range(len(dates) - 1):
            if (dates[i] - dates[i + 1]).days == 1:
                temp_streak += 1
                longest_streak = max(longest_streak, temp_streak)
            else:
                temp_streak = 1
        
        longest_streak = max(longest_streak, temp_streak, current_streak)
        
        return current_streak, longest_streak
    
    except Exception as e:
        print(f"Warning: Could not calculate streak from commits: {e}")
        return 0, 0

def update_readme(problems_solved, current_streak, longest_streak):
    """Update README.md with calculated stats"""
    
    # Read current README
    try:
        with open('README.md', 'r') as f:
            content = f.read()
    except FileNotFoundError:
        print("❌ README.md not found")
        return False
    
    # Find and extract the started date if it exists
    started_match = re.search(r'\| 📅 Started On \| (.*?) \|', content)
    if started_match and started_match.group(1).strip() not in ['—', '']:
        started_date = started_match.group(1).strip()
    else:
        started_date = datetime.now().strftime('%B %d, %Y')
    
    # Update the stats table
    stats_pattern = r'\| Metric \| Count \|[\s\S]*?\| 📅 Started On \| .*? \|'
    
    new_stats = f"""| Metric | Count |
|---|---|
| ✅ Problems Solved | {problems_solved} |
| 🔥 Current Streak | {current_streak} days |
| 🏆 Longest Streak | {longest_streak} days |
| 📅 Started On | {started_date} |"""
    
    updated_content = re.sub(stats_pattern, new_stats, content)
    
    # Write updated README
    with open('README.md', 'w') as f:
        f.write(updated_content)
    
    print(f"✅ Updated stats:")
    print(f"   Problems Solved: {problems_solved}")
    print(f"   Current Streak: {current_streak} days")
    print(f"   Longest Streak: {longest_streak} days")
    return True

if __name__ == "__main__":
    print("Counting POTD problem folders...")
    problems_solved = count_potd_folders()
    print(f"Found {problems_solved} POTD folders")
    
    print("Calculating streaks from commit history...")
    current_streak, longest_streak = calculate_streak_from_commits()
    
    success = update_readme(problems_solved, current_streak, longest_streak)
    
    if success:
        print("🎉 README updated successfully!")
    else:
        print("❌ Failed to update README")
        exit(1)
